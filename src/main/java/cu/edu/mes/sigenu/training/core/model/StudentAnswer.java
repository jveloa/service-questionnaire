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
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Junior
 */
@Entity
@Table(name = "student_answer", catalog = "training", schema = "public")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "StudentAnswer.findAll", query = "SELECT s FROM StudentAnswer s")
    , @NamedQuery(name = "StudentAnswer.findById", query = "SELECT s FROM StudentAnswer s WHERE s.id = :id")
    , @NamedQuery(name = "StudentAnswer.findByStudentSigenuId", query = "SELECT s FROM StudentAnswer s WHERE s.studentSigenuId = :studentSigenuId")})
public class StudentAnswer implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @Column(name = "student_sigenu_id")
    private String studentSigenuId;
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
    
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getStudentSigenuId() {
        return studentSigenuId;
    }

    public void setStudentSigenuId(String studentSigenuId) {
        this.studentSigenuId = studentSigenuId;
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
