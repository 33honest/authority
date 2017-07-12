
/**
 * @author huoshan
 * created by 2017年7月12日 下午2:27:39
 * 
 */
public class TestUrl {
	public static void main(String[] args) {
		String url = "http://localcodeblog.net:8000/profile/write";
		String uri = "/profile/write";
		int index = url.indexOf(uri);
		System.out.println(url.substring(0, index));
	}
}
