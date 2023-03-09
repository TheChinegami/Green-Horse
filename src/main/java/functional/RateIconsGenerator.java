package functional;

public class RateIconsGenerator {
	
	public static String getStars(float rate) {
		
		int i = 0;
		String stars = "";
		
		while(i<5 && rate>=1) {
			stars = stars + "<i class=\"fa-solid fa-star\"></i>";
			i++;
			rate--;
		}
		
		if(i<5 && rate >= 0.5) {
			stars = stars + "<i class=\"fa-solid fa-star-half-stroke\"></i>";
			i++;
		}
		
		while(i<5) {
			stars = stars + "<i class=\"fa-regular fa-star\"></i>";
			i++;
		}
		
		return stars;
		
	}
}
