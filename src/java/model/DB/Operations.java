/**
 * БГУИР Кафедра экономической информатики Курсовой проект ВСРПП Снаров Иван гр. 272303
 */
package model.DB;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author kiskin
 */
@Entity
@Table(name = "Operations")
@XmlRootElement
@NamedQueries({
	@NamedQuery(name = "Operations.findAll", query = "SELECT o FROM Operations o"),
	@NamedQuery(name = "Operations.findById", query = "SELECT o FROM Operations o WHERE o.id = :id"),
	@NamedQuery(name = "Operations.findByWallet", query = "SELECT o FROM Operations o WHERE o.wallet = :wallet"),
	@NamedQuery(name = "Operations.findByType", query = "SELECT o FROM Operations o WHERE o.type = :type"),
	@NamedQuery(name = "Operations.findByTime", query = "SELECT o FROM Operations o WHERE o.time = :time"),
	@NamedQuery(name = "Operations.findByValue", query = "SELECT o FROM Operations o WHERE o.value = :value")})
public class Operations implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID")
	private Integer id;
	@Column(name = "Wallet")
	private Integer wallet;
	@Column(name = "Type")
	private String type;
	@Column(name = "Time")
    @Temporal(TemporalType.TIMESTAMP)
	private Date time;
	@Column(name = "Value")
	private Integer value;

	public Operations() {
	}

	public Operations(Integer id) {
		this.id = id;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getWallet() {
		return wallet;
	}

	public void setWallet(Integer wallet) {
		this.wallet = wallet;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Date getTime() {
		return time;
	}

	public void setTime(Date time) {
		this.time = time;
	}

	public Integer getValue() {
		return value;
	}

	public void setValue(Integer value) {
		this.value = value;
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
		if (!(object instanceof Operations)) {
			return false;
		}
		Operations other = (Operations) object;
		if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "model.DB.Operations[ id=" + id + " ]";
	}
	
}
