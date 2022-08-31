package br.infnet.edu.gabriwebee.gabriwebee.controllers;

import br.infnet.edu.gabriwebee.gabriwebee.services.AmazonService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;

@Controller
@RequestMapping("/buckets")
public class BucketsController {

    @Autowired
    AmazonService amazonService;

    @GetMapping
    public String listar(Model model) {

        try {
            amazonService.getFileFrom("gabecrbuck", "users/2/profilePic.jpeg");

            var bkcs = amazonService.listOfBuckets();
            model.addAttribute("bucketsList", bkcs);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return "buckets/lista";
    }

    @PostMapping("/upload")
    public String MyUploadByMulti(@RequestPart(value = "bucketName") String bucketName,
                                  @RequestPart(value = "file") MultipartFile file) {
        var result = amazonService.uploadFile(bucketName, file);
        System.out.println(result);
        // return "redirect:/" + listar(model);
        return "redirect:/buckets";
    }

    @GetMapping("lista/arquivos/{bucketName}")
    public String listarArqv(@PathVariable String bucketName, Model model) {
        var result = amazonService.listObjects(bucketName);
        model.addAttribute("bucketFiles", result);

        System.out.println(result);
        return "buckets/listaArquivos";

    }

}
