package e01b.sol1;

import java.util.*;
import java.util.stream.Collectors;

public class ExamSystemBuilderImpl implements ExamSystemBuilder {

    private static record ExamSystemImpl(
        List<Integer> exams,
        Map<Integer, Set<String>> enrollments,
        Map<Integer, Map<String, Integer>> evaluations
    ) implements ExamSystem {

        @Override
        public Set<String> students() {
            return this.enrollments
                    .values()
                    .stream()
                    .flatMap(Collection::stream)
                    .collect(Collectors.toSet());
        }

        @Override
        public Optional<Integer> studentEvaluation(int examId, String student){
            return Optional.ofNullable(this.evaluations.get(examId).get(student));
        }

        @Override
        public Map<String, Optional<Integer>> examEvaluations(int exam) {
            return this.enrollments().get(exam).stream().collect(Collectors.toMap(
                student -> student,
                student -> studentEvaluation(exam, student)
            ));
        }

        private Set<Integer> allEvaluations(String student){
            return this.exams
                    .stream()
                    .map(id -> studentEvaluation(id, student))
                    .filter(Optional::isPresent)
                    .map(Optional::get)
                    .collect(Collectors.toSet());
        }

        @Override
        public Map<String, Integer> overallBestEvaluations() {
            return this.students()
                    .stream()
                    .filter(student -> !allEvaluations(student).isEmpty())
                    .collect(Collectors.toMap(
                        student -> student,
                        student -> Collections.max(allEvaluations(student))));
        }
        
    }

    private ExamSystemImpl examSystem = new ExamSystemImpl(new LinkedList<>(), new HashMap<>(), new HashMap<>());
    private boolean examOpened = false;

    @Override
    public ExamSystemBuilder newExam(int id) {
        if (this.examOpened || this.examSystem.exams().contains(id)){
            throw new IllegalArgumentException();
        }
        this.examSystem.exams().add(id);
        this.examOpened = true;
        this.examSystem.enrollments.put(id, new HashSet<>());
        this.examSystem.evaluations.put(id, new HashMap<>());
        return this;
    }

    @Override
    public ExamSystemBuilder enrollStudent(String student) {
        if (!this.examOpened){
            throw new IllegalStateException();
        }
        this.examSystem.enrollments.get(this.examSystem.exams.getLast()).add(student);
        return this;
    }

    @Override
    public ExamSystemBuilder exitExam() {
        if (!this.examOpened){
            throw new IllegalStateException();
        }
        this.examOpened = false;
        return this;
    }

    @Override
    public ExamSystemBuilder addEvaluation(int examId, String student, int evaluation) {
        if (this.examOpened){
            throw new IllegalStateException();
        }
        if (!this.examSystem.enrollments.get(examId).contains(student)){
            throw new IllegalArgumentException();
        }
        this.examSystem.evaluations().get(examId).put(student, evaluation);
        return this;
    }

    @Override
    public ExamSystem build() {
        return this.examSystem;
    }

}
