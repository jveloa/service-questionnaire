/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cu.edu.mes.sigenu.training.core.model;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
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
@Table(name = "question", catalog = "training", schema = "public")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Question.findAll", query = "SELECT q FROM Question q")
    , @NamedQuery(name = "Question.findById", query = "SELECT q FROM Question q WHERE q.id = :id")
    , @NamedQuery(name = "Question.findByNameQuestion", query = "SELECT q FROM Question q WHERE q.nameQuestion = :nameQuestion")
    , @NamedQuery(name = "Question.findByIsEvaluationQuestion", query = "SELECT q FROM Question q WHERE q.isEvaluationQuestion = :isEvaluationQuestion")
    , @NamedQuery(name = "Question.findByIsSpecificQuestion", query = "SELECT q FROM Question q WHERE q.isSpecificQuestion = ïsSpecificQuestion")
    , @NamedQuery(name = "Question.findByIsCanceled", query = "SELECT q FROM Question q WHERE q.isCanceled = :isCanceled")})
public class Question implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @Column(name = "name_question")
    private String nameQuestion;
    @Basic(optional = false)
    @Column(name = "is_evaluation_question")
    private boolean isEvaluationQuestion;
    @Basic(optional = false)
    @Column(name = "is_specific_question")
    private boolean isSpecificQuestion;
    @Basic(optional = false)
    @Column(name = "is_canceled")
    private boolean isCanceled;
    @JoinTable(name = "questionnaire_question", joinColumns = {
        @JoinColumn(name = "question_id", referencedColumnName = "id")}, inverseJoinColumns = {
        @JoinColumn(name = "questionnaire_id", referencedColumnName = "id")})
    @ManyToMany
    private List<Questionnaire> questionnaireList;
    @JoinColumn(name = "group_question_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private GroupQuestion groupQuestionId;
    @OneToMany(mappedBy = "questionId")
    private List<QuestionAnswer> questionAnswerList;

    public Question() {
    }

    public Question(Integer id) {
        this.id = id;
    }
    
    public Question(Integer id, String nameQuestion) {
    	this.id = id;
        this.nameQuestion = nameQuestion;
    }

    public Question(Integer id, String nameQuestion, boolean isEvaluationQuestion,
    		boolean isSpecificQuestion, boolean isCanceled) {
        this.id = id;
        this.nameQuestion = nameQuestion;
        this.isEvaluationQuestion = isEvaluationQuestion;
        this.isSpecificQuestion = isSpecificQuestion;
        this.isCanceled = isCanceled;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNameQuestion() {
        return nameQuestion;
    }

    public void setNameQuestion(String nameQuestion) {
        this.nameQuestion = nameQuestion;
    }

    public boolean getIsEvaluationQuestion() {
        return isEvaluationQuestion;
    }

    public void setIsEvaluationQuestion(boolean isEvaluationQuestion) {
        this.isEvaluationQuestion = isEvaluationQuestion;
    }
    
    public boolean getIsSpecificQuestion() {
        return isSpecificQuestion;
    }

    public void setIsSpecificQuestion(boolean isSpecificQuestion) {
        this.isSpecificQuestion = isSpecificQuestion;
    }
    
    public boolean getIsCanceled() {
        return isCanceled;
    }

    public void setIsCanceled(boolean isCanceled) {
        this.isCanceled = isCanceled;
    }

    @XmlTransient
    public List<Questionnaire> getQuestionnaireList() {
        return questionnaireList;
    }

    public void setQuestionnaireList(List<Questionnaire> questionnaireList) {
        this.questionnaireList = questionnaireList;
    }

    public GroupQuestion getGroupQuestionId() {
        return groupQuestionId;
    }

    public void setGroupQuestionId(GroupQuestion groupQuestionId) {
        this.groupQuestionId = groupQuestionId;
    }

    @XmlTransient
    public List<QuestionAnswer> getQuestionAnswerList() {
        return questionAnswerList;
    }

    public void setQuestionAnswerList(List<QuestionAnswer> questionAnswerList) {
        this.questionAnswerList = questionAnswerList;
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
        if (!(object instanceof Question)) {
            return false;
        }
        Question other = (Question) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "cu.edu.mes.sigenu.training.core.model.Question[ id=" + id + " ]";
    }    
}
