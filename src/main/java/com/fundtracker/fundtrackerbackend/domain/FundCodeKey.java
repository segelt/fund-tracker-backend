package com.fundtracker.fundtrackerbackend.domain;

import java.io.Serializable;
import java.time.LocalDate;

public class FundCodeKey implements Serializable {
	private static final long serialVersionUID = 3384594070697628419L;

	private String fonKodu;
	
	private LocalDate tarih;
	
	public FundCodeKey(){
		fonKodu = "def";
		tarih = LocalDate.now();
	}
	
    public FundCodeKey(String FonKodu, LocalDate Tarih) {
        this.fonKodu = FonKodu;
        this.tarih = Tarih;
    }
    
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + (fonKodu.hashCode());
        result = prime * result + ((tarih == null) ? 0 : tarih.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        FundCodeKey other = (FundCodeKey) obj;
        if (this.fonKodu == null) {
            if (other.fonKodu != null)
                return false;
        } else if (!this.fonKodu.equals(other.fonKodu))
            return false;
        
        if (this.tarih == null) {
            if (other.tarih != null)
                return false;
        } else if (!this.tarih.equals(other.tarih))
            return false;
        return true;
    }
}
