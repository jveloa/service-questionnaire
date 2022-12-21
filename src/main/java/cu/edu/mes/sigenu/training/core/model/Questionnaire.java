/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cu.edu.mes.sigenu.training.core.model;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Junior
 */
@Entity
@Table(name = "questionnaire", catalog = "training", schema = "public")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Questionnaire.findAll", query = "SELECT q FROM Questionnaire q")
    , @NamedQuery(name = "Questionnaire.findById", query = "SELECT q FROM Questionnaire q WHERE q.id = :id")
    , @NamedQuery(name = "Questionnaire.findByName", query = "SELECT q FROM Questionnaire q WHERE q.name = :name")
    , @NamedQuery(name = "Questionnaire.findByCareerSigenuId", query = "SELECT q FROM Questionnaire q WHERE q.careerSigenuId = :careerSigenuId")})
public class Questionnaire implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @Column(name = "name")
    private String name;
    @Basic(optional = false)
    @Column(name = "career_sigenu_id")
    private String careerSigenuId;
    @ManyToMany(mappedBy = "questionnaireList")
    private List<Question> questionList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "questionnarieId")
    private List<QuestionnarieStudent> questionnarieStudentList;

    public Questionnaire() {
    }

    public Questionnaire(Integer id) {
        this.id = id;
    }

    public Questionnaire(Integer id, String name, String careerSigenuId) {
        this.id = id;
        this.name = name;
        this.careerSigenuId = careerSigenuId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCareerSigenuId() {
        return careerSigenuId;
    }

    public void setCareerSigenuId(String careerSigenuId) {
        this.careerSigenuId = careerSigenuId;
    }

    @XmlTransient
    public List<Question> getQuestionList() {
        return questionList;
    }

    public void setQuestionList(List<Question> questionList) {
        this.questionList = questionList;
    }

    @XmlTransient
    public List<QuestionnarieStudent> getQuestionnarieStudentList() {
        return questionnarieStudentList;
    }

    public void setQuestionnarieStudentList(List<QuestionnarieStudent> questionnarieStudentList) {
        this.questionnarieStudentList = questionnarieStudentList;
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
        if (!(object instanceof Questionnaire)) {
            return false;
        }
        Questionnaire other = (Questionnaire) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "cu.edu.mes.sigenu.training.core.model.Questionnaire[ id=" + id + " ]";
    }
    
}
