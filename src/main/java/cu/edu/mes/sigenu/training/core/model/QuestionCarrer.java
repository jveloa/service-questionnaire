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
@Table(name = "question_carrer", catalog = "training", schema = "public")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "QuestionCarrer.findAll", query = "SELECT q FROM QuestionCarrer q")
    , @NamedQuery(name = "QuestionCarrer.findById", query = "SELECT q FROM QuestionCarrer q WHERE q.id = :id")
    , @NamedQuery(name = "QuestionCarrer.findByCareerSigenuId", query = "SELECT q FROM QuestionCarrer q WHERE q.careerSigenuId = :careerSigenuId")})
public class QuestionCarrer implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Column(name = "career_sigenu_id")
    private String careerSigenuId;
    @JoinColumn(name = "question_id", referencedColumnName = "id")
    @ManyToOne
    private Question questionId;

    public QuestionCarrer() {
    }

    public QuestionCarrer(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCareerSigenuId() {
        return careerSigenuId;
    }

    public void setCareerSigenuId(String careerSigenuId) {
        this.careerSigenuId = careerSigenuId;
    }

    public Question getQuestionId() {
        return questionId;
    }

    public void setQuestionId(Question questionId) {
        this.questionId = questionId;
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
        if (!(object instanceof QuestionCarrer)) {
            return false;
        }
        QuestionCarrer other = (QuestionCarrer) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "cu.edu.mes.sigenu.training.core.model.QuestionCarrer[ id=" + id + " ]";
    }
    
}
