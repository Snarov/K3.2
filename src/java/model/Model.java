/**
 * БГУИР Кафедра экономической информатики Курсовой проект ВСРПП Снаров Иван гр. 272303
 */
package model;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import model.DB.Cars;
import model.DB.CarsJpaController;
import model.DB.ColorPrices;
import model.DB.ColorPricesJpaController;
import model.DB.Models;
import model.DB.ModelsJpaController;
import model.DB.Operations;
import model.DB.OperationsJpaController;
import model.DB.Users;
import model.DB.UsersJpaController;
import model.DB.Wallets;
import model.DB.WalletsJpaController;
import org.json.simple.JSONObject;

/**
 *
 * @author Kiskin класс, представляющий слой приложения, содержащий бизнес-логику
 */
public class Model {

	static EntityManagerFactory em;
	private static UsersJpaController _ujpac;
	private static WalletsJpaController _wjpac;
	private static CarsJpaController _cjpac;
	private static ColorPricesJpaController _cpjpac;
	private static ModelsJpaController _mjpac;
	private static OperationsJpaController _ojpac;

	public static Users CheckUser(String username, String pwd) {
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
	private static EntityManagerFactory GetEm() {
		return Persistence.createEntityManagerFactory("PaintServicePU");
	}

	/**
	 * @return the ujpac
	 */
	private static UsersJpaController GetUjpac() {
		if (ujpac == null) {
			ujpac = new UsersJpaController(getEm());
		}
		return ujpac;
	}

	/**
	 * @return the wjpac
	 */
	private static WalletsJpaController GetWjpac() {
		if (wjpac == null) {
			wjpac = new WalletsJpaController(getEm());
		}
		return wjpac;
	}

	private static CarsJpaController GetCjpac() {
		if (cjpac == null) {
			cjpac = new CarsJpaController(getEm());
		}
		return cjpac;
	}

	/**
	 * @return the cpjpac
	 */
	private static ColorPricesJpaController GetCpjpac() {
		if (cpjpac == null) {
			cpjpac = new ColorPricesJpaController(getEm());
		}
		return cpjpac;
	}

	/**
	 * @return the mjpac
	 */
	private static ModelsJpaController GetMjpac() {
		if (mjpac == null) {
			mjpac = new ModelsJpaController(getEm());
		}
		return mjpac;

	}

	/**
	 * @return the ojpac
	 */
	private static OperationsJpaController GetOjpac() {
		if (ojpac == null) {
			ojpac = new OperationsJpaController(getEm());
		}
		return ojpac;
	}

	/**
	 *
	 * @param username
	 * @param userPw
	 * @return true if registration completed, else if user with that name already exists
	 */
	public static Users RegisterNewUser(String username, String userPw) {
		Users newUser = new Users();
		Wallets wallet = new Wallets();

		wallet.setAmount(50000);
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
			Cars car = new Cars();
			car.setColor1(new byte[]{-1, 0, 0});
			car.setColor2(new byte[]{127, 127, 127});
			car.setModel(4);
			getCjpac().create(car);
			
			getUjpac().create(newUser);
			getWjpac().create(wallet);
			newUser.setWallet(wallet.getId());
			newUser.setCar(car.getId()); 
			getUjpac().edit(newUser);
		} catch (Throwable exc) {
			return null;
		}

		return newUser;
	}

	public static Wallets GetUserWallet(int userid) {
		Users user = getUjpac().findUsers(userid);
		Wallets wallet = getWjpac().findWallets(user.getWallet());
		return wallet;
	}

	public static Cars GetUserCar(Integer userid) {
		Users user = getUjpac().findUsers(userid);
		Cars car = getCjpac().findCars(user.getCar());
		return car;
	}

	public static int[] GetColorPrices() {
		List<ColorPrices> colorPrices = getCpjpac().findColorPricesEntities();

		int[] retval = new int[colorPrices.size()];

		for (int i = 0; i < retval.length; i++) {
			retval[i] = colorPrices.get(i).getPrice();
		}

		return retval;
	}

	public static int[] GetModelPrices() {
		List<Models> models = getMjpac().findModelsEntities();

		int[] retval = new int[models.size()];

		for (Models model : models) {
			retval[model.getId() - 1] = model.getPrice();
		}

		return retval;
	}

	public static boolean PerformOp(JSONObject operation, int userid) {
		try {

			Users user = getUjpac().findUsers(userid);

			if (operation.get("orderModel") != null && !operation.get("orderModel").equals(operation.get("currentModel"))) {
				Cars car = getCjpac().findCars(user.getCar());
				car.setModel(((Long) operation.get("orderModel")).intValue() + 1);
				getCjpac().edit(car);

				Operations op = new Operations();
				op.setWallet(user.getWallet());
				op.setType("Продажа автомобиля");
				op.setValue(((Long) operation.get("sellCarCost")).intValue());
				op.setTime(new Date());

				getOjpac().create(op);

				Operations op2 = new Operations();
				op2.setWallet(user.getWallet());
				op2.setType("Покупка автомобиля");
				op2.setValue(((Long) operation.get("modelCost")).intValue());
				op2.setTime(new Date());

				getOjpac().create(op2);
			}

			if (operation.get("color1Cost") != null && (long) operation.get("color1Cost") != 0L) {
				String color = (String) operation.get("orderColor1");
				byte[] colorByte = new byte[3];
				for (int i = 0, j = 1; i < 3; i++, j += 2) {
					int buf = Integer.parseInt(color.substring(j, j + 2), 16);
					colorByte[i] = (byte) buf;
				}

				Cars car = getCjpac().findCars(user.getCar());
				car.setColor1(colorByte);
				getCjpac().edit(car);

				Operations op = new Operations();
				op.setWallet(user.getWallet());
				op.setType("Покраска кузова");
				op.setValue(((Long) operation.get("color1Cost")).intValue());
				op.setTime(new Date());

				getOjpac().create(op);
			}

			if (operation.get("color2Cost") != null && (long) operation.get("color2Cost") != 0L) {
				String color = (String) operation.get("orderColor2");
				byte[] colorByte = new byte[3];
				for (int i = 0, j = 1; i < 3; i++, j += 2) {
					int buf = Integer.parseInt(color.substring(j, j + 2), 16);
					colorByte[i] = (byte) buf;
				}

				Cars car = getCjpac().findCars(user.getCar());
				car.setColor2(colorByte);
				getCjpac().edit(car);

				Operations op = new Operations();
				op.setWallet(user.getWallet());
				op.setType("Покраска колесных дисков");
				op.setValue(((Long) operation.get("color2Cost")).intValue());
				op.setTime(new Date());

				getOjpac().create(op);
			}

			if ((long) operation.get("cost") != 0L) {
				Wallets wallet = getWjpac().findWallets(user.getWallet());
				wallet.setAmount(((Long) (wallet.getAmount() - (long) operation.get("cost"))).intValue());
				getWjpac().edit(wallet);
			}
		} catch (Throwable exc) {
			return false;
		}

		return true;
	}

	public static List<Operations> GetOperations(int userid) {
		Users user = getUjpac().findUsers(userid);

		return getOjpac().findOperationsByWallet(user.getWallet());

	}

	public static void SetColorPrices(int[] cp) {
		List<ColorPrices> colorPrices = getCpjpac().findColorPricesEntities();

		for (int i = 0; i < colorPrices.size(); i++) {
			try {
				colorPrices.get(i).setPrice(cp[i]);
				getCpjpac().edit(colorPrices.get(i));
			} catch (Exception ex) {
				Logger.getLogger(Model.class.getName()).log(Level.SEVERE, null, ex);
			}
		}
	}

	public static void SetModelPrice(String carName, int price) {
		Models model = getMjpac().findModelByName(carName);
		if (model != null) {
			try {
				model.setPrice(price);
				getMjpac().edit(model);
			} catch (Exception ex) {
				Logger.getLogger(Model.class.getName()).log(Level.SEVERE, null, ex);
			}
		}
	}

	public static boolean MoneyTransfer(String username, int amount) {
		try {
			Users user = getUjpac().findUserByName(username);
			
			if (user == null) {
				return false;
			}
			
			Wallets wallet = getWjpac().findWallets(user.getWallet());
			wallet.setAmount(wallet.getAmount() + amount);
			getWjpac().edit(wallet);
			
			Operations op = new Operations();
			op.setWallet(user.getWallet());
			op.setType("Пополнение кошелька");
			op.setValue(amount);
			op.setTime(new Date());
			getOjpac().create(op);
			
			return true;
		} catch (Exception ex) {
			return false;
		}

	}
}
