/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cu.edu.mes.sigenu.training.core.model;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author fpicayo
 */
@Entity
@Table(name = "student_answer")
@NamedQueries({
    @NamedQuery(name = "StudentAnswer.findAll", query = "SELECT s FROM StudentAnswer s")})
public class StudentAnswer implements Serializable {

    private static final long serialVersionUID = 1L;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2147483647)
    @Column(name = "student_sigenu_id")
    private String studentSigenuId;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @JoinColumn(name = "question_answer_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private QuestionAnswer questionAnswerId;

    public StudentAnswer() {
    }

    public StudentAnswer(Integer id) {
        this.id = id;
    }

    public StudentAnswer(Integer id, String studentSigenuId) {
        this.id = id;
        this.studentSigenuId = studentSigenuId;
    }

    public String getStudentSigenuId() {
        return studentSigenuId;
    }

    public void setStudentSigenuId(String studentSigenuId) {
        this.studentSigenuId = studentSigenuId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public QuestionAnswer getQuestionAnswerId() {
        return questionAnswerId;
    }

    public void setQuestionAnswerId(QuestionAnswer questionAnswerId) {
        this.questionAnswerId = questionAnswerId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof StudentAnswer)) {
            return false;
        }
        StudentAnswer other = (StudentAnswer) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "cu.edu.mes.sigenu.training.core.model.StudentAnswer[ id=" + id + " ]";
    }
    
}
