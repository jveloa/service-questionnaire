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
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author fpicayo
 */
@Entity
@Table(name = "question")
@NamedQueries({
    @NamedQuery(name = "Question.findAll", query = "SELECT q FROM Question q")})
public class Question implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2147483647)
    @Column(name = "question")
    private String question;
    @Size(max = 2147483647)
    @Column(name = "description")
    private String description;
    @JoinTable(name = "questionnaire_question", joinColumns = {
        @JoinColumn(name = "question_id", referencedColumnName = "id")}, inverseJoinColumns = {
        @JoinColumn(name = "questionnaire_id", referencedColumnName = "id")})
    @ManyToMany
    private List<Questionnaire> questionnaireList;
    @JoinColumn(name = "id_group_question", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private GroupQuestion idGroupQuestion;
    @OneToMany(mappedBy = "questionId")
    private List<QuestionAnswer> questionAnswerList;
    @OneToMany(mappedBy = "questionId")
    private List<QuestionCarrer> questionCarrerList;

    public Question() {
    }

    public Question(Integer id) {
        this.id = id;
    }

    public Question(Integer id, String question) {
        this.id = id;
        this.question = question;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Questionnaire> getQuestionnaireList() {
        return questionnaireList;
    }

    public void setQuestionnaireList(List<Questionnaire> questionnaireList) {
        this.questionnaireList = questionnaireList;
    }

    public GroupQuestion getIdGroupQuestion() {
        return idGroupQuestion;
    }

    public void setIdGroupQuestion(GroupQuestion idGroupQuestion) {
        this.idGroupQuestion = idGroupQuestion;
    }

    public List<QuestionAnswer> getQuestionAnswerList() {
        return questionAnswerList;
    }

    public void setQuestionAnswerList(List<QuestionAnswer> questionAnswerList) {
        this.questionAnswerList = questionAnswerList;
    }

    public List<QuestionCarrer> getQuestionCarrerList() {
        return questionCarrerList;
    }

    public void setQuestionCarrerList(List<QuestionCarrer> questionCarrerList) {
        this.questionCarrerList = questionCarrerList;
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
