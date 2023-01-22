/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cu.edu.mes.sigenu.training.core.model;

import java.io.Serializable;
import java.util.Date;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Junior
 */
@Entity
@Table(name = "questionnaire_student", catalog = "training", schema = "public")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "QuestionnaireStudent.findAll", query = "SELECT q FROM QuestionnaireStudent q")
    , @NamedQuery(name = "QuestionnaireStudent.findById", query = "SELECT q FROM QuestionnaireStudent q WHERE q.id = :id")
    , @NamedQuery(name = "QuestionnaireStudent.findByStudentSigenuId", query = "SELECT q FROM QuestionnaireStudent q WHERE q.studentSigenuId = :studentSigenuId")
    , @NamedQuery(name = "QuestionnaireStudent.findByDoneDate", query = "SELECT q FROM QuestionnaireStudent q WHERE q.doneDate = :doneDate")})
public class QuestionnaireStudent implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @Column(name = "student_sigenu_id")
    private String studentSigenuId;
    @Column(name = "done_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date doneDate;
    @JoinColumn(name = "questionnaire_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Questionnaire questionnaireId;

    public QuestionnaireStudent() {
    }

    public QuestionnaireStudent(Integer id) {
        this.id = id;
    }

    public QuestionnaireStudent(Integer id, String studentSigenuId) {
        this.id = id;
        this.studentSigenuId = studentSigenuId;
    }
    
    public QuestionnaireStudent(Integer id, String studentSigenuId, Date doneDate) {
        this.id = id;
        this.studentSigenuId = studentSigenuId;
        this.doneDate = doneDate;
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

    public Date getDoneDate() {
        return doneDate;
    }

    public void setDoneDate(Date doneDate) {
        this.doneDate = doneDate;
    }

    public Questionnaire getQuestionnaireId() {
        return questionnaireId;
    }

    public void setQuestionnaireId(Questionnaire questionnaireId) {
        this.questionnaireId = questionnaireId;
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
        if (!(object instanceof QuestionnaireStudent)) {
            return false;
        }
        QuestionnaireStudent other = (QuestionnaireStudent) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "cu.edu.mes.sigenu.training.core.model.QuestionnaireStudent[ id=" + id + " ]";
    }
}
