package ge.tbcacademy.data.models.ergast;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ErgastResponse{

	@JsonProperty("MRData")
	private MRData mRData;

	public void setMRData(MRData mRData){
		this.mRData = mRData;
	}

	public MRData getMRData(){
		return mRData;
	}

	@Override
 	public String toString(){
		return 
			"ErgastResponse{" + 
			"mRData = '" + mRData + '\'' + 
			"}";
		}
}