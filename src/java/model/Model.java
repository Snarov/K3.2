/**
 * БГУИР Кафедра экономической информатики Курсовой проект ВСРПП Снаров Иван гр. 272303
 */
package model;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.List;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import model.DB.Cars;
import model.DB.CarsJpaController;
import model.DB.ColorPrices;
import model.DB.ColorPricesJpaController;
import model.DB.Models;
import model.DB.ModelsJpaController;
import model.DB.Users;
import model.DB.UsersJpaController;
import model.DB.Wallets;
import model.DB.WalletsJpaController;

/**
 *
 * @author Kiskin класс, представляющий слой приложения, содержащий бизнес-логику
 */
public class Model {

	static EntityManagerFactory em;
	private static UsersJpaController ujpac;
	private static WalletsJpaController wjpac;
	private static CarsJpaController cjpac;
	private static ColorPricesJpaController cpjpac;
	private static ModelsJpaController mjpac;

	public static Users checkUser(String username, String pwd) {
		Users user = getUjpac().findUserByName(username);
		byte[] encPwd;

		if (user != null) {
			MessageDigest messageDigest = null;

			try {
				messageDigest = MessageDigest.getInstance("SHA-256");
				messageDigest.reset();
				messageDigest.update(pwd.getBytes());
				encPwd = messageDigest.digest();

				return Arrays.equals(user.getPassword(), encPwd) ? user : null;
			} catch (NoSuchAlgorithmException e) {
				System.err.println(e.getMessage());
			}
		}

		return user;
	}

	/**
	 * @return the em
	 */
	private static EntityManagerFactory getEm() {
		return Persistence.createEntityManagerFactory("PaintServicePU");
	}

	/**
	 * @return the ujpac
	 */
	private static UsersJpaController getUjpac() {
		if (ujpac == null) {
			ujpac = new UsersJpaController(getEm());
		}
		return ujpac;
	}

	/**
	 * @return the wjpac
	 */
	private static WalletsJpaController getWjpac() {
		if (wjpac == null) {
			wjpac = new WalletsJpaController(getEm());
		}
		return wjpac;
	}

	private static CarsJpaController getCjpac() {
		if (cjpac == null) {
			cjpac = new CarsJpaController(getEm());
		}
		return cjpac;
	}

	/**
	 * @return the cpjpac
	 */
	private static ColorPricesJpaController getCpjpac() {
		if (cpjpac == null) {
			cpjpac = new ColorPricesJpaController(getEm());
		}
		return cpjpac;
	}

	/**
	 * @return the mjpac
	 */
	private static ModelsJpaController getMjpac() {
		if (mjpac == null) {
			mjpac = new ModelsJpaController(getEm());
		}
		return mjpac;

	}

	/**
	 *
	 * @param username
	 * @param userPw
	 * @return true if registration completed, else if user with that name already exists
	 */
	public static Users registerNewUser(String username, String userPw) {
		Users newUser = new Users();
		Wallets wallet = new Wallets();

		newUser.setName(username);

		byte[] encPwd;

		MessageDigest messageDigest = null;

		try {
			messageDigest = MessageDigest.getInstance("SHA-256");
			messageDigest.reset();
			messageDigest.update(userPw.getBytes());
			encPwd = messageDigest.digest();

			newUser.setPassword(encPwd);
		} catch (NoSuchAlgorithmException e) {
			System.err.println(e.getMessage());
		}
		try {
			getUjpac().create(newUser);
			getWjpac().create(wallet);
			newUser.setWallet(wallet.getId());
			getUjpac().edit(newUser);
		} catch (Throwable exc) {
			return null;
		}

		return newUser;
	}

	public static Wallets getUserWallet(int userid) {
		Users user = getUjpac().findUsers(userid);
		Wallets wallet = getWjpac().findWallets(user.getWallet());
		return wallet;
	}

	public static Cars getUserCar(Integer userid) {
		Users user = getUjpac().findUsers(userid);
		Cars car = getCjpac().findCars(user.getCar());
		return car;
	}

	public static int[] getColorPrices() {
		List<ColorPrices> colorPrices = getCpjpac().findColorPricesEntities();
		
		int[] retval = new int[colorPrices.size()];
		
		for(int i = 0; i < retval.length; i++){
			retval[i] = colorPrices.get(i).getPrice();
		}
		
		return retval;
	}

	public static int[] getModelPrices() {
		List<Models> models = getMjpac().findModelsEntities();
		
		int[] retval = new int[models.size()];
		
		for(Models model : models){
			retval[model.getId() - 1] = model.getPrice();
		}
		
		return retval;
	}

}
