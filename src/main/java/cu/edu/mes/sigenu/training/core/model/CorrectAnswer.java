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
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Junior
 */
@Entity
@Table(name = "correct_answer", catalog = "training", schema = "public")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CorrectAnswer.findAll", query = "SELECT c FROM CorrectAnswer c")
    , @NamedQuery(name = "CorrectAnswer.findById", query = "SELECT c FROM CorrectAnswer c WHERE c.id = :id")})
public class CorrectAnswer implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @JoinColumn(name = "question_answer_id", referencedColumnName = "id")
    @OneToOne
    private QuestionAnswer questionAnswerId;

    public CorrectAnswer() {
    }

    public CorrectAnswer(Integer id) {
        this.id = id;
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
        if (!(object instanceof CorrectAnswer)) {
            return false;
        }
        CorrectAnswer other = (CorrectAnswer) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "cu.edu.mes.sigenu.training.core.model.CorrectAnswer[ id=" + id + " ]";
    }
}
