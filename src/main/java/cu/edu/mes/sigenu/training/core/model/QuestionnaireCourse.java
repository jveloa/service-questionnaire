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
* @author Lauren
*/
@Entity
@Table(name = "questionnaire_course", catalog = "training", schema = "public")
@XmlRootElement
@NamedQueries({
   @NamedQuery(name = "QuestionnaireCourse.findAll", query = "SELECT q FROM QuestionnaireCourse q")
   , @NamedQuery(name = "QuestionnaireCourse.findById", query = "SELECT q FROM QuestionnaireCourse q WHERE q.id = :id")
   , @NamedQuery(name = "QuestionnaireCourse.findByCourseSigenuId", query = "SELECT q FROM QuestionnaireCourse q WHERE q.courseSigenuId = :courseSigenuId")})
public class QuestionnaireCourse implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
	@Basic(optional = false)
    @Column(name = "course_sigenu_id")
    private String courseSigenuId;
	@JoinColumn(name = "questionnaire_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Questionnaire questionnaireId;
	
	public QuestionnaireCourse() {
	}
	
	public QuestionnaireCourse(Integer id) {
		this.id = id;
	}
	
	public QuestionnaireCourse(Integer id, String courseSigenuId) {
		this.id = id;
		this.courseSigenuId = courseSigenuId;
	}
	
	public Integer getId() {
		return id;
	}
	
	public void setId(Integer id) {
		this.id = id;
	}
	
	public String getCourseSigenuId() {
		return courseSigenuId;
	}
	
	public void setCourseSigenuId(String courseSigenuId) {
		this.courseSigenuId = courseSigenuId;
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
        if (!(object instanceof QuestionnaireCourse)) {
            return false;
        }
        QuestionnaireCourse other = (QuestionnaireCourse) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "cu.edu.mes.sigenu.training.core.model.QuestionnaireCourse[ id=" + id + " ]";
    }
}
