/**
 * БГУИР Кафедра экономической информатики Курсовой проект ВСРПП Снаров Иван гр. 272303
 */
package model.DB;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author kiskin
 */
@Entity
@Table(name = "Models")
@XmlRootElement
@NamedQueries({
	@NamedQuery(name = "Models.findAll", query = "SELECT m FROM Models m"),
	@NamedQuery(name = "Models.findById", query = "SELECT m FROM Models m WHERE m.id = :id"),
	@NamedQuery(name = "Models.findByName", query = "SELECT m FROM Models m WHERE m.name = :name"),
	@NamedQuery(name = "Models.findByPrice", query = "SELECT m FROM Models m WHERE m.price = :price")})
public class Models implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID")
	private Integer id;
	@Basic(optional = false)
    @Column(name = "Name")
	private String name;
	@Basic(optional = false)
    @Column(name = "Price")
	private int price;

	public Models() {
	}

	public Models(Integer id) {
		this.id = id;
	}

	public Models(Integer id, String name, int price) {
		this.id = id;
		this.name = name;
		this.price = price;
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

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
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
		if (!(object instanceof Models)) {
			return false;
		}
		Models other = (Models) object;
		if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "model.DB.Models[ id=" + id + " ]";
	}
	
}
