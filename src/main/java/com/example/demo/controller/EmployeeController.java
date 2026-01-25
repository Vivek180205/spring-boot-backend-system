package com.example.demo.controller;

import com.example.demo.model.*;
import com.example.demo.service.*;
import jakarta.servlet.http.HttpSession;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/employee")
public class EmployeeController {

    //Constructor Injection..........
    private final EmployeeService service;
    private final EmployeeAssetService service2;
    private final EmployeeResumeService service3;
    private final EmployeeAudioService service4;
    private final EmployeeVideoService service5;
    private final EmployeeBankDetailsService service6;
    private final SessionManagement session;

    public EmployeeController(EmployeeService service, EmployeeAssetService service2, EmployeeResumeService service3, EmployeeAudioService service4, EmployeeVideoService service5, EmployeeBankDetailsService service6, SessionManagement session) {
        this.service = service;
        this.service2 = service2;
        this.service3 = service3;
        this.service4 = service4;
        this.service5 = service5;
        this.service6 = service6;
        this.session = session;
    }
    //.................

//NORMAL EMPLOYEE TABLE CRUD
    @GetMapping
    public List<Employee> getAll(){
        return service.findAll();
    }

    @GetMapping("/{id}")
    public Employee getById(@PathVariable int id){
        return service.findById(id);
    }


    @PutMapping("/update/{id}")
    public String update(@PathVariable int id, @RequestBody Employee e){

        e.setId(id);
       return service.updateByValue(e);
    }

    @DeleteMapping("/delete/{id}")
    public String delete(@PathVariable int id){
        if(service.deleteByValue(id))
            return "Succesfully Deleted";
        else
            return "Not Found";
    }

//CSV FILE FETCHING.............................
    @GetMapping("/assets/all")
    public List<EmployeeWithAssetsDTO> getAllAsset() {
        return service2.getAllEmployeesWithAssets();
    }

    @GetMapping("/assets/{empId}")
    public ResponseEntity<?> getOneWithAssets(@PathVariable int empId) {
        Optional<EmployeeWithAssetsDTO> opt = service2.getEmployeeWithAssets(empId);
        if (opt.isPresent()) {
            return ResponseEntity.ok(opt.get());
        } else {
            return ResponseEntity.badRequest().body("Employee not found");
        }
    }
//RESUME FETCHING (PDF) N UPLOAD............................
    @GetMapping("/resume/{empId}")
    public ResponseEntity<byte[]> getOneWithResume(@PathVariable int empId){
        EmployeeResume emp = service3.getPdf(empId);
        if(emp == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok()
                .header("Content-type","application/pdf")
                .header("Content-Disposition",
                        "inline; filename:\""+ emp.getFileName() +"\"")
                .body(emp.getResumePDF());
    }

    @PostMapping(value = "/saveResume/{empId}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<String> uploadResume(@PathVariable int empId, @RequestPart("file") MultipartFile file) throws IOException {

        service3.saveResume(empId, file);
        return ResponseEntity.ok("Resume uploaded successfully");
    }

//AUDIO UPLOAD ANS FETCH......................
    @GetMapping("/getAudio/{empId}")
    public ResponseEntity<InputStreamResource> getAudioById(@PathVariable int empId){
        EmployeeAudio emp = service4.getAudioById(empId);
        ByteArrayInputStream bis = new ByteArrayInputStream(emp.getAudioData());
        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(emp.getContentType()))
                .header("content-disposition",
                        "inline; filename:\""+emp.getFileName()+"\"")
                .body(new InputStreamResource(bis));
    }

    @PostMapping(value = "/saveAudio/{empId}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<String> uploadAudio(@PathVariable int empId, @RequestPart("file") MultipartFile file) throws IOException {
        service4.saveAudio(empId,file);
        return ResponseEntity.ok("Audio Uploaded Succesfully");
    }

//VIDEO UPLOAD AND FETCH................
    @PostMapping(value = "/video/upload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<String>  uploadVideo(
            @RequestParam int empId,
            @RequestPart("file") MultipartFile file) throws IOException {

        service5.saveVideo(
                empId,
                file.getOriginalFilename(),
                file.getContentType(),
                file.getBytes()
        );
        return ResponseEntity.ok("Video uploaded");
    }

    @GetMapping("/video/{empId}")
    public ResponseEntity<InputStreamResource> fetchVideo(@PathVariable int empId){
        EmployeeVideo emp =service5.getVideo(empId);
        ByteArrayInputStream bis = new ByteArrayInputStream(emp.getVideoData());

        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(emp.getContentType()))
                .header(HttpHeaders.CONTENT_DISPOSITION,
                        "inline; filename:\""+ emp.getFileName() +"\"")
                .body(new InputStreamResource(bis));
    }

//Employee Bank Details Upload and Fetch

    @PostMapping("/bank-detail/{empId}")
    public String saveBankDetails(@PathVariable Long empId, @RequestBody EmployeeBankDetails request) {

        request.setEmpId(empId);
        service6.save(request);

        return "Employee bank details saved successfully";
    }

    @GetMapping("/bank-detail/{empId}")
    public EmployeeBankDetails getBankDetails(@PathVariable Long empId) {

        return service6.getByEmpId(empId);
    }

// Session Management
// MANUAL AUTH (for learning/debug)

    @PostMapping("/bank-detail/login")
    public String login(@RequestBody LoginRequest req, HttpSession ses){
        EmployeeBankDetails emp = session.validateData(req.getUsername(), req.getPassword());
 
        if(emp == null){
            return "Invalid Credentials";
        }
        ses.setAttribute("EMP_ID", emp.getEmpId());
        ses.setMaxInactiveInterval(30);

        return "Login SuccessFull";
    }

    @GetMapping("/dashboard")
    public String dashboard(HttpSession ses){
        Object empId = ses.getAttribute("EMP_ID");
        if (empId == null) {
            return "Session Expired. Login Again";
        }
        return "Welcome employee id: " + empId;

    }

    @PostMapping("/bank-detail/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "Logged out successfully";
    }

}


