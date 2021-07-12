package com.fundtracker.fundtrackerbackend.repositories;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.fundtracker.fundtrackerbackend.domain.FundCodeKey;
import com.fundtracker.fundtrackerbackend.domain.HistoricData;
import com.fundtracker.fundtrackerbackend.dto.ISortedDataDTO;
import com.fundtracker.fundtrackerbackend.dto.LatestFundSummaryDTO;

@Repository
public interface HistoricDataRepository extends JpaRepository<HistoricData, FundCodeKey>{

	List<HistoricData> findAllByFonKodu(String fonKodu);

	List<HistoricData> findAllByFonKoduAndTarihGreaterThanEqualAndTarihLessThanEqual(String fundCode,
			LocalDate startDate, LocalDate endDate);
	
	List<HistoricData> findAllByFonKoduAndTarihGreaterThanEqual(String fundCode, LocalDate startDate);
	
	HistoricData findFirstByFonKoduOrderByTarihDesc(String fonKodu);
	
	@Query(value= "select * from historicdata h where fonkodu = :FonKodu and tarih = :Tarih", nativeQuery = true)
    HistoricData findByFonKoduAndTarihExact(@Param("FonKodu") String FonKodu, @Param("Tarih") LocalDate tarih);
	
	
	@Query("select new com.fundtracker.fundtrackerbackend.dto.LatestFundSummaryDTO("
			+ "f.fonKodu, h.birimPayDegeri, h.tarih, f.fonTuru) from Fund f INNER JOIN HistoricData h on f.fonKodu = h.fonKodu "
			+ "where h.fonKodu = :FonKodu order by h.tarih desc")		
	List<LatestFundSummaryDTO> findLatestInfoAboutFund(@Param("FonKodu") String FonKodu, Pageable pageable);
	
	@Query(value = "WITH "+ 
					" lastRow AS "+ 
					" (SELECT * FROM historicdata "+
					" WHERE tarih > '2021-02-01' AND (fonkodu, tarih) IN (SELECT fonkodu, MAX(tarih) FROM historicdata GROUP BY fonkodu)), "+
				" priorData AS (SELECT MAX(lastRow.tarih - ( :toTime )\\:\\:interval * 1) AS eskiTarih, fonKodu FROM lastRow GROUP BY fonKodu), "+
				" priorDatavalues AS (SELECT * FROM priorData pd JOIN LATERAL (select birimpaydegeri AS eskibirimpaydegeri, tarih, fonkodu AS eskifonkodu FROM historicdata "+ 
					" WHERE fonkodu = pd.fonkodu AND tarih <= pd.eskitarih ORDER BY tarih DESC LIMIT 1) ea ON pd.fonkodu = ea.eskifonkodu "+
					" GROUP BY (ea.eskifonkodu, pd.eskitarih, pd.fonkodu, ea.eskibirimpaydegeri, ea.tarih)) "+
					" SELECT lastRow.fonkodu, "+
					" eskibirimpaydegeri, "+
					" birimpaydegeri, "+
					" fonadi, "+
					" (birimpaydegeri - eskibirimpaydegeri) AS difference, "+
					" ((birimpaydegeri - eskibirimpaydegeri)*100) /NULLIF(eskibirimpaydegeri, 0) AS percentageGain, "+
					" eskitarih, "+
					" lastRow.tarih "+
				" FROM priorDatavalues pdv inner join lastRow ON "+ 
					" pdv.fonkodu = lastRow.fonkodu inner join fon on "+
					" pdv.fonkodu = fon.fonkodu ORDER BY percentageGain ASC LIMIT :limit", nativeQuery = true)
	List<ISortedDataDTO> GetSortListASC(@Param("toTime") String toTime, @Param("limit")int limit);
	
	@Query(value = "WITH "+ 
			" lastRow AS "+ 
			" (SELECT * FROM historicdata "+
			" WHERE tarih > '2021-02-01' AND (fonkodu, tarih) IN (SELECT fonkodu, MAX(tarih) FROM historicdata GROUP BY fonkodu)), "+
		" priorData AS (SELECT MAX(lastRow.tarih - ( :toTime )\\:\\:interval * 1) AS eskiTarih, fonKodu FROM lastRow GROUP BY fonKodu), "+
		" priorDatavalues AS (SELECT * FROM priorData pd JOIN LATERAL (select birimpaydegeri AS eskibirimpaydegeri, tarih, fonkodu AS eskifonkodu FROM historicdata "+ 
			" WHERE fonkodu = pd.fonkodu AND tarih <= pd.eskitarih ORDER BY tarih DESC LIMIT 1) ea ON pd.fonkodu = ea.eskifonkodu "+
			" GROUP BY (ea.eskifonkodu, pd.eskitarih, pd.fonkodu, ea.eskibirimpaydegeri, ea.tarih)) "+
			" SELECT lastRow.fonkodu, "+
			" eskibirimpaydegeri, "+
			" birimpaydegeri, "+
			" fonadi, "+
			" (birimpaydegeri - eskibirimpaydegeri) AS difference, "+
			" ((birimpaydegeri - eskibirimpaydegeri)*100) /NULLIF(eskibirimpaydegeri, 0) AS percentageGain, "+
			" eskitarih, "+
			" lastRow.tarih "+
		" FROM priorDatavalues pdv inner join lastRow ON "+ 
			" pdv.fonkodu = lastRow.fonkodu inner join fon on "+
			" pdv.fonkodu = fon.fonkodu ORDER BY percentageGain DESC LIMIT :limit", nativeQuery = true)
	List<ISortedDataDTO> GetSortListDESC(@Param("toTime") String toTime, @Param("limit")int limit);

}
