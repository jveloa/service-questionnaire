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
@Table(name = "group_question", catalog = "training", schema = "public")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "GroupQuestion.findAll", query = "SELECT g FROM GroupQuestion g")
    , @NamedQuery(name = "GroupQuestion.findById", query = "SELECT g FROM GroupQuestion g WHERE g.id = :id")
    , @NamedQuery(name = "GroupQuestion.findByDescription", query = "SELECT g FROM GroupQuestion g WHERE g.description = :description")
    , @NamedQuery(name = "GroupQuestion.findByNameGroup", query = "SELECT g FROM GroupQuestion g WHERE g.nameGroup = :nameGroup")})
public class GroupQuestion implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Column(name = "description")
    private String description;
    @Basic(optional = false)
    @Column(name = "name_group")
    private String nameGroup;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idGroupQuestion")
    private List<Question> questionList;

    public GroupQuestion() {
    }

    public GroupQuestion(Integer id) {
        this.id = id;
    }

    public GroupQuestion(Integer id, String nameGroup) {
        this.id = id;
        this.nameGroup = nameGroup;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getNameGroup() {
        return nameGroup;
    }

    public void setNameGroup(String nameGroup) {
        this.nameGroup = nameGroup;
    }

    @XmlTransient
    public List<Question> getQuestionList() {
        return questionList;
    }

    public void setQuestionList(List<Question> questionList) {
        this.questionList = questionList;
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
        if (!(object instanceof GroupQuestion)) {
            return false;
        }
        GroupQuestion other = (GroupQuestion) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "cu.edu.mes.sigenu.training.core.model.GroupQuestion[ id=" + id + " ]";
    }
    
}
