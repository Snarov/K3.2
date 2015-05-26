/**
 * БГУИР Кафедра экономической информатики Курсовой проект ВСРПП Снаров Иван гр. 272303
 */
package model.DB;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
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
@Table(name = "ColorPrices")
@XmlRootElement
@NamedQueries({
	@NamedQuery(name = "ColorPrices.findAll", query = "SELECT c FROM ColorPrices c"),
	@NamedQuery(name = "ColorPrices.findByPrice", query = "SELECT c FROM ColorPrices c WHERE c.price = :price")})
public class ColorPrices implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
    @Basic(optional = false)
    @Lob
    @Column(name = "Color")
	private byte[] color;
	@Basic(optional = false)
    @Column(name = "Price")
	private int price;

	public ColorPrices() {
	}

	public ColorPrices(byte[] color) {
		this.color = color;
	}

	public ColorPrices(byte[] color, int price) {
		this.color = color;
		this.price = price;
	}

	public byte[] getColor() {
		return color;
	}

	public void setColor(byte[] color) {
		this.color = color;
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
		hash += (color != null ? color.hashCode() : 0);
		return hash;
	}

	@Override
	public boolean equals(Object object) {
		// TODO: Warning - this method won't work in the case the id fields are not set
		if (!(object instanceof ColorPrices)) {
			return false;
		}
		ColorPrices other = (ColorPrices) object;
		if ((this.color == null && other.color != null) || (this.color != null && !this.color.equals(other.color))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "model.DB.ColorPrices[ color=" + color + " ]";
	}
	
}
