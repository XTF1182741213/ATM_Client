package model.md;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import model.db.AccountDAO;
import model.db.DealDAO;

public class SeqCreate {
	private DealDAO dealdao;
	private GETDate date = new GETDate();

	public String create(String account) {
		// TODO Auto-generated method stub

		// TODO Auto-generated method stub
		dealdao = new DealDAO();// 查询最后的一个单子

		List<Map<String, Object>> params = new ArrayList<Map<String, Object>>();

		Map<String, Object> map = new HashMap<String, Object>();
		String nowdate = date.getNowYear() + date.getNowMonth()
				+ date.getNowDay();
		map.put("name", "seq");
		map.put("rela", "like");
		map.put("value", "'%" + nowdate + "%'");

		params.add(map);
		map = new HashMap<String, Object>();
		map.put("name", "acct");
		map.put("rela", "=");
		map.put("value", "'" + account + "'");

		params.add(map);
		String seq = "";
		String seqlast = "";

		try {

			List<Deal> result;

			result = dealdao.query(params);
			if (result.size()>0) {
				String seqquery = result.get(result.size()-1).getSeq();
				System.out.println(seqquery);
				int end = seqquery.length();
				seqlast = seqquery.substring(end - 4, end);
				seq=account+nowdate+String.format("%0" + 4 + "d", Integer.parseInt(seqlast) + 1);
			} else {
				seq=account+nowdate+"0001";
			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return seq;
	}

}
