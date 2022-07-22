package net.springboot.javaguides.service;

import net.springboot.javaguides.entity.Student;
import net.springboot.javaguides.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ReportService {
    @Autowired
    private StudentRepository repository;

    public String exportReport(String format, int categoryId) throws FileNotFoundException, JRException {
        String path = "/home/baom/";
        List<Student> students = repository.findAll();

        File file = ResourceUtils.getFile("classpath:bikes.jrxml");
        JasperReport jasperReport = JasperCompileManager.compileReport(file.getAbsolutePath());
        JRBeanCollectionDataSource ds = new JRBeanCollectionDataSource((students));
        Map<String, Object> params = new HashMap<>();
        params.put("category_id", categoryId);
        JasperPrint jp = JasperFillManager.fillReport(jasperReport, params, ds);

        JasperExportManager.exportReportToPdfFile(jp, path+"students.pdf");
        return path+"students.pdf";
    }
}
