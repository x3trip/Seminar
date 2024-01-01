import java.util.ArrayList;
import java.util.List;
/**
Класс PromoClient описывает акционного клиента для автомата горячихнапитков.
* Включает поле название акции и номер клиента в акции (поле статическое).
* Реализует интерфейс iReturnOrder для возврата товаров.
* Если количество участников акции превышает установленный порог,происходит отказ в обслуживании для акционного клиента.
* Сохраняет в файл лог работы магазина, включая возвраты товара.
*/
public class PromoClient implements iReturnOrder {
/** Название акции. */
private static String promotionName;
/** Номер клиента в акции. */
private static int clientNumber;
/** Количество участников акции. */
private static int participantsNumber;
/** Список заказанных товаров. */
private List orders = new ArrayList();
/** Файл лога работы магазина. */
private static final String LOG_FILE = "log.txt";
/**
* Метод для установки названия акции.
* @param name название акции.
*/
public static void setPromotionName(String name) {
promotionName = name;
}
/**
* Метод для установки номера клиента в акции.
* @param number номер клиента в акции.
*/
public static void setClientNumber(int number) {
if (number > participantsNumber) {
    System.out.println("Извините, количество участников акции уже достигломаксимального значения.");
} else {
clientNumber = number;
}
}
/**
* Метод для установки общего количества участников акции.
* @param number количество участников акции.
*/
public static void setParticipantsNumber(int number) {
participantsNumber = number;
}
/**
* Метод для заказа горячего напитка.
* @param drinkType тип горячего напитка.
*/
public void orderDrink(String drinkType) {
orders.add(drinkType);
}
/**
* Метод для возврата заказанного товара.
* @param item заказанный товар.
*/
@Override
public void returnItem(String item) {
if (orders.contains(item)) {
orders.remove(item);
logOrder(item + " был возвращен клиентом " + clientNumber + " в рамках акции" + promotionName);
}
}
/**
* Метод для возврата нескольких товаров.
* @param items список товаров.
*/
@Override
public void returnItems(List items) {
for (String item : items) {
if (orders.contains(item)) {
orders.remove(item);
logOrder(item + " был возвращен клиентом " + clientNumber + " в рамках акции " + promotionName);
}
}
}
/**
* Метод для сохранения лога работы магазина в файл.
* @param order заказ или возврат товара, который нужно сохранить в логе.
*/
private void logOrder(String order) {
try {
Files.write(Paths.get(LOG_FILE), (order + "n").getBytes(),StandardOpenOption.APPEND);
} catch (IOException e) {
e.printStackTrace();
}
}
}
