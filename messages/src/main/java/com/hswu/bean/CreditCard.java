package com.hswu.bean;

import android.os.Parcel;
import android.os.Parcelable;

public class CreditCard extends BaseBean implements Parcelable {

	private String bankName;
	private String cardName;
	private String cardNumber;
	private String cvv2;
	private String indate;
	private String limit;

	
	public CreditCard()
	{
		super();
	}
	
	
	public CreditCard(String bankName, String cardName, String cardNumber, String cvv2, String indate, String limit) {
		super();
		this.bankName = bankName;
		this.cardName = cardName;
		this.cardNumber = cardNumber;
		this.cvv2 = cvv2;
		this.indate = indate;
		this.limit = limit;
	}



	public CreditCard(Parcel source) {
		this.id = source.readInt();
		this.bankName = source.readString();
		this.cardName = source.readString();
		this.cardNumber = source.readString();
		this.cvv2 = source.readString();
		this.indate = source.readString();
		this.limit = source.readString();
	}

	
	
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getBankName() {
		return bankName;
	}

	public void setBankName(String bankName) {
		this.bankName = bankName;
	}

	public String getCardName() {
		return cardName;
	}

	public void setCardName(String cardName) {
		this.cardName = cardName;
	}

	public String getCardNumber() {
		return cardNumber;
	}

	public void setCardNumber(String cardNumber) {
		this.cardNumber = cardNumber;
	}

	public String getCvv2() {
		return cvv2;
	}

	public void setCvv2(String cvv2) {
		this.cvv2 = cvv2;
	}

	public String getIndate() {
		return indate;
	}

	public void setIndate(String indate) {
		this.indate = indate;
	}
	public String getLimit() {
		return limit;
	}


	public void setLimit(String limit) {
		this.limit = limit;
	}


	@Override
	public int describeContents() {
		return 0;
	}
	
	
	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeInt(this.id);
		dest.writeString(this.bankName);
		dest.writeString(this.cardName);
		dest.writeString(this.cardNumber);
		dest.writeString(this.cvv2);
		dest.writeString(this.indate);
		dest.writeString(this.limit);
	}

	public static final Parcelable.Creator<CreditCard> CREATOR = new Creator<CreditCard>() {
		
		@Override
		public CreditCard[] newArray(int size) {
			return new CreditCard[size];
		}
		
		@Override
		public CreditCard createFromParcel(Parcel source) {
			return new CreditCard(source);
		}
	};
	
}
