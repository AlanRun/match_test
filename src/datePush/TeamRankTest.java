package datePush;

import java.io.IOException;

import com.jdd.fm.core.exception.AesException;

import bdapi.Bd229Test;
import bdapi.Ct204Test;
import bdapi.Jl2031Test;
import bdapi.Jz202Test;

public class TeamRankTest {

	public static void main(String[] args) throws IOException, AesException {
//		Jz202Test.getTeadRankAndHis();
//		Bd229Test.getTeadRankAndHis();
//		Ct204Test.getTeadRankAndHis();
		Jl2031Test.getTeadRankAndHis();
	}
}
