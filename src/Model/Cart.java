package Model;

import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * ���ﳵ����
 * 
 * 
 */
public class Cart {

	// ���ﳵ����
	// �������:Map��key������Ʒpid,value:������ LinkedHashMap����� HashMap�������
	private Map<String, CartItem> map = new LinkedHashMap<String, CartItem>();

	// Cart��������һ����cartItems����.
	public Collection<CartItem> getCartItems() {
		// Map�����Ƴ���Ʒ������ҳ�������Ϣ�鷳�����Ե���ȡ��value��ֵ����������һ������Collection
		return map.values();
	}

	// �����ܼ�:
	private double total;

	public double getTotal() {
		return total;
	}

	// ���ﳵ�Ĺ���:
	// 1.����������ӵ����ﳵ
	public void addCart(CartItem cartItem, String dormid) {
		// �жϹ��ﳵ���Ƿ��Ѿ����ڸù�����:
		/*
		 * * �������: * �������� * �ܼ� = �ܼ� + ������С�� * ���������: * ��map����ӹ����� * �ܼ� = �ܼ� +
		 * ������С��
		 */
		// ��ø���Ʒ��Ϣ��¼��id.����������¥��
		String id = cartItem.getId();

		// �жϹ��ﳵ���Ƿ��Ѿ����ڸù�����:
		if (map.containsKey(id)) {
			// ����
			CartItem _cartItem = map.get(id);// ��ù��ﳵ��ԭ���Ĺ�����
			if (_cartItem.getDormid().equals(dormid)) {
				// �������Ʒ����ͬһ������¥�������Ѵ��ڵ���Ʒ����������
				_cartItem.setCount(_cartItem.getCount() + cartItem.getCount());
			} else {
				// �����������ͬһ�����ᣬ������һ����Ʒ��
				map.put(id, cartItem);
			}
		} else {
			// �����ڣ�������һ����Ʒ��
			map.put(id, cartItem);
		}
		// �����ܼƵ�ֵ
		total += cartItem.getSubtotal();
	}

	// 2.�ӹ��ﳵ�Ƴ�������
	public void removeCart(String deleteid) {
		// ���������Ƴ����ﳵ: ���ص��Ǳ��Ƴ�������Ķ���
		CartItem cartItem = map.remove(deleteid);
		// �ܼ� = �ܼ� -�Ƴ��Ĺ�����С��:
		total -= cartItem.getSubtotal();
	}

	// 3.��չ��ﳵ
	public void clearCart() {
		// �����й��������
		map.clear();
		// ���ܼ�����Ϊ0
		total = 0;
	}
}
