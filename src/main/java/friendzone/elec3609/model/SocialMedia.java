package friendzone.elec3609.model;

import java.util.ArrayList;
import java.util.List;

public class SocialMedia {
	public enum Provider{
		FACEBOOK ("Facebook"),
		TWITTER ("Twitter"),
		LINKEDIN ("LinkedIn"),
		PINTEREST ("Pinterest"),
		GOOGLEPLUS ("Google Plus+"), 
		MYSPACE ("MySpace"),
		DEVIANTART ("DeviantArt"),
		LIVEJOURNAL ("LiveJournal");
		
		private final String name;
		private Provider(String name){
			this.name = name;
		}
		public String toString(){
			return name;
		}

		public static List<String> getNames(){
			List<String> names = new ArrayList<String>();
			for (Provider provider : Provider.values()){
				names.add(provider.toString());
			}
			return names;
		}
		
		public static Provider findMatch(String name){
			Provider matchingProvider = null;
			for (Provider provider : Provider.values()){
				if (provider.name.equals(name)){
					matchingProvider = provider;
					break;
				}
			}
			return matchingProvider;
		}
		
	} 
	Provider provider;
	String address;
	public SocialMedia (Provider provider, String address){
		setProvider(provider);
		setAddress(address);
	}
	public Provider getProvider(){
		return provider;
	}
	public String getAddress(){
		return address;
	}
	public void setProvider(Provider provider) throws IllegalArgumentException{
		if (provider == null)
			throw new IllegalArgumentException();
		this.provider = provider;
	}
	public void setAddress(String address) throws IllegalArgumentException{
		if (address == null)
			throw new IllegalArgumentException();
		this.address = address;
	}
	public String toString(){
		if (provider == null)
			return null;
		return provider.toString() + ": " + address;
	}
}