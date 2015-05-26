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
@Table(name = "Users")
@XmlRootElement
@NamedQueries({
	@NamedQuery(name = "Users.findAll", query = "SELECT u FROM Users u"),
	@NamedQuery(name = "Users.findById", query = "SELECT u FROM Users u WHERE u.id = :id"),
	@NamedQuery(name = "Users.findByName", query = "SELECT u FROM Users u WHERE u.name = :name"),
	@NamedQuery(name = "Users.findByWallet", query = "SELECT u FROM Users u WHERE u.wallet = :wallet"),
	@NamedQuery(name = "Users.findByCar", query = "SELECT u FROM Users u WHERE u.car = :car")})
public class Users implements Serializable {
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
    @Lob
    @Column(name = "Password")
	private byte[] password;
	@Basic(optional = false)
    @Column(name = "Wallet")
	private int wallet;
	@Basic(optional = false)
    @Column(name = "Car")
	private int car;

	public Users() {
	}

	public Users(Integer id) {
		this.id = id;
	}

	public Users(Integer id, String name, byte[] password, int wallet, int car) {
		this.id = id;
		this.name = name;
		this.password = password;
		this.wallet = wallet;
		this.car = car;
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

	public byte[] getPassword() {
		return password;
	}

	public void setPassword(byte[] password) {
		this.password = password;
	}

	public int getWallet() {
		return wallet;
	}

	public void setWallet(int wallet) {
		this.wallet = wallet;
	}

	public int getCar() {
		return car;
	}

	public void setCar(int car) {
		this.car = car;
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
		if (!(object instanceof Users)) {
			return false;
		}
		Users other = (Users) object;
		if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "model.DB.Users[ id=" + id + " ]";
	}
	
}
