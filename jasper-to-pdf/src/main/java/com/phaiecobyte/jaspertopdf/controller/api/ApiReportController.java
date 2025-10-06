package com.phaiecobyte.jaspertopdf.controller.api;

import com.phaiecobyte.jaspertopdf.model.Student;
import com.phaiecobyte.jaspertopdf.service.StudentService;
import com.phaiecobyte.jaspertopdf.util.ReportUtil;
import lombok.RequiredArgsConstructor;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/report")
public class ApiReportController {
    private final StudentService studentService;

    @GetMapping("/{studentRollNumber}")
    public ResponseEntity<byte[]> generateStudentReport(@PathVariable(name = "studentRollNumber") Long rollNumber) throws IOException{
        try {
            List<Student> students = null;
            if(rollNumber != null){
                students = List.of(studentService.getStudentById(rollNumber));
            }else {
                students.add(new Student());
            }

            String jasperPath = new File("jasper-to-pdf/src/main/resources/templates/reports/student/StudentCard.jasper").getAbsolutePath();

            HashMap<String, Object> parameters = new HashMap<>();
            JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(students.get(0).getScoreCards());

            byte[] pdfData = ReportUtil.generateReport(students,jasperPath,parameters);

            return ResponseEntity.ok()
                    .header(HttpHeaders.CONTENT_DISPOSITION,"attachment; filename=student_report.pdf")
                    .header(HttpHeaders.CONTENT_TYPE,"application/pdf")
                    .body(pdfData);
        } catch (JRException e) {
            throw new RuntimeException(e);
        }
    }
}
