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
import javax.persistence.Lob;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author kiskin
 */
@Entity
@Table(name = "Cars")
@XmlRootElement
@NamedQueries({
	@NamedQuery(name = "Cars.findAll", query = "SELECT c FROM Cars c"),
	@NamedQuery(name = "Cars.findById", query = "SELECT c FROM Cars c WHERE c.id = :id"),
	@NamedQuery(name = "Cars.findByModel", query = "SELECT c FROM Cars c WHERE c.model = :model")})
public class Cars implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID")
	private Integer id;
	@Basic(optional = false)
    @Column(name = "Model")
	private int model;
	@Basic(optional = false)
    @Lob
    @Column(name = "Color1")
	private byte[] color1;
	@Basic(optional = false)
    @Lob
    @Column(name = "Color2")
	private byte[] color2;

	public Cars() {
	}

	public Cars(Integer id) {
		this.id = id;
	}

	public Cars(Integer id, int model, byte[] color1, byte[] color2) {
		this.id = id;
		this.model = model;
		this.color1 = color1;
		this.color2 = color2;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public int getModel() {
		return model;
	}

	public void setModel(int model) {
		this.model = model;
	}

	public byte[] getColor1() {
		return color1;
	}

	public void setColor1(byte[] color1) {
		this.color1 = color1;
	}

	public byte[] getColor2() {
		return color2;
	}

	public void setColor2(byte[] color2) {
		this.color2 = color2;
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
		if (!(object instanceof Cars)) {
			return false;
		}
		Cars other = (Cars) object;
		if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "model.DB.Cars[ id=" + id + " ]";
	}
	
}
