package com.pivo.hotelo.infrastructure.report;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pivo.hotelo.api.DTO.pdfDTO.RentPdfDTO;
import com.pivo.hotelo.domain.service.RentReportService;
import com.pivo.hotelo.domain.service.RentService;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

@Service
public class PdfRentReportService implements RentReportService {

	@Autowired
	private RentService rentService;
	
	
	@Override
	public byte[] rentReport(Long rentId) {
		try {
		
		var inputStream = this.getClass().getResourceAsStream("/rentPdf/reserva.jasper");
		
		var parametros = new HashMap<String, Object>();
		parametros.put("REPORT_LOCALE", new Locale("pt", "BR"));
		
		var rent = rentService.findRentForPdf(rentId);
		
		List<RentPdfDTO> rentList = Collections.singletonList(rent);
		
		var dataSource = new JRBeanCollectionDataSource(rentList);
		
		var jasperPrint = JasperFillManager.fillReport(inputStream, parametros, dataSource);
		
		
			return JasperExportManager.exportReportToPdf(jasperPrint);
		} catch (JRException e) {
			throw new ReportException("Não foi possivel gerar comprovante de reserva", e);
		}
	}

	
}
