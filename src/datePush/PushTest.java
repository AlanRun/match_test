package datePush;

import bdapi.Bd229Test;
import bdapi.Ct204Test;
import bdapi.Jl2031Test;
import bdapi.Jz202Test;

public class PushTest {

	public static void main(String[] args) throws Exception {
//		Jz202Test.getTeadRankAndHis();
//		Bd229Test.getTeadRankAndHis();
//		Ct204Test.getTeadRankAndHis();
//		Jl2031Test.getTeadRankAndHis();
		try {
			JzSpTest.compareJddSpToGf_JZ();
//			JzSpTest.getFinalSoccerFromData("20170921");
//			JzSpTest.getFinalSoccerFromData("20170922");
		} catch (Exception e) {
		}

		try {
			JlSpTest.compareJddSpToGf_JL();
//			JlSpTest.getFinalSpFromData("20170919");
		} catch (Exception e) {
		}

		try {
			CtSpTest.compareJddSpToGf_Ct();
//			CtSpTest.getFinalSpFromData("2017137");
//			CtSpTest.getFinalSpFromData("2017138");
		} catch (Exception e) {
		}

		try {
			BdSpTest.compareJddSpToGf_Bd();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		try {
			BdSpTest.getFinalSpAndSoccor("171005");
		} catch (Exception e) {
		}

	}
}
