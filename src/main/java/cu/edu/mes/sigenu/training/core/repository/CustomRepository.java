package cu.edu.mes.sigenu.training.core.repository;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Repository;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class CustomRepository {

    @Autowired
    private EntityManager entityManager;


    public List<Object[]> responsibilityReport(Integer year){
        Query q = entityManager.createNativeQuery(
                "SELECT student_answer.student_sigenu_id, question.question,answer.answer" +
                    " from student_answer" +
                    " join question_answer  on student_answer.question_answer_id = question_answer.id " +
                    " join question on question_answer.question_id = question.id" +
                    " join answer on question_answer.answer_id = answer.id" +
                    " join group_question on  question.group_question_id = group_question.id" +
                    " join questionnarie_student on student_answer.student_sigenu_id = questionnarie_student.student_sigenu_id" +
                    " where (question.question = 'Tienes interés en apoyar en la dirección de las organizaciones estudiantiles '" +
                    " or question.question = 'Experiencias en dirección de FEEM o UJC'" +
                    " or question.question = 'Organización política a la que perteneces' )" +
                    " and date_part('year',questionnarie_student.done_date) = :year" +
                    " order by student_answer.student_sigenu_id");

        q.setParameter("year", year);

        List<Object[]> results = q.getResultList();
        return results;
    }

    public List<Object[]> studentSportList(Integer year){
        Query q = entityManager.createNativeQuery(
            "select student_answer.student_sigenu_id,question.question" +
            " from student_answer" +
            " join question_answer on question_answer.id = student_answer.question_answer_id" +
            " join question on question.id = question_answer.question_id" +
            " join answer on answer.id = question_answer.answer_id" +
            " join questionnarie_student on student_answer.student_sigenu_id = questionnarie_student.student_sigenu_id" +
            " join group_question on question.group_question_id = group_question.id" +
            " where answer.answer = 'Si' and" +
            " group_question.name_group = 'Deportes' and" +
            " date_part('year',questionnarie_student.done_date) = :year" +
            " order by student_answer.student_sigenu_id");

        q.setParameter("year", year);

        List<Object[]> results = q.getResultList();
        return results;
    }

    public List<Object[]> studentArtList(Integer year){
        Query q = entityManager.createNativeQuery(
            " select student_answer.student_sigenu_id,question.question" +
                " from student_answer\n" +
                " join question_answer on question_answer.id = student_answer.question_answer_id " +
                " join question on question.id = question_answer.question_id" +
                " join answer on answer.id = question_answer.answer_id" +
                " join questionnarie_student on student_answer.student_sigenu_id = questionnarie_student.student_sigenu_id" +
                " join group_question on question.group_question_id = group_question.id" +
                " where answer.answer = 'Si' and " +
                " group_question.name_group = 'Manifestaciones artísticas' and" + " " +
                " date_part('year',questionnarie_student.done_date) = :year" +
                " order by student_answer.student_sigenu_id");

        q.setParameter("year", year);

        List<Object[]> results = q.getResultList();
        return results;
    }

    public List<Object[]> deportArtListByStudent(String studentSigenuId){
        Query q = entityManager.createNativeQuery(
            " SELECT group_question.name_group,question.question" +
                " from student_answer\n" +
                " join question_answer on student_answer.question_answer_id = question_answer.id" +
                " join question on question_answer.question_id = question.id" +
                " join answer on question_answer.answer_id = answer.id" +
                " join group_question on  question.group_question_id = group_question.id" +
                " join questionnarie_student on student_answer.student_sigenu_id = questionnarie_student.student_sigenu_id " +
                " where student_answer.student_sigenu_id = :student_sigenu_id and" +
                " answer.answer = 'Si' and " +
                "(group_question.name_group = 'Manifestaciones artísticas' " +
                " or group_question.name_group = 'Deportes')");

        q.setParameter("student_sigenu_id", studentSigenuId);

        List<Object[]> results = q.getResultList();
        return results;
    }



}
