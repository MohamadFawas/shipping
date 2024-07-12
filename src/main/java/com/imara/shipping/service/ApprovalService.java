package com.imara.shipping.service;

import com.imara.shipping.config.AppConstants;
import com.imara.shipping.model.Approval;
import com.imara.shipping.model.ApprovalImage;
import com.imara.shipping.repository.ApprovalRepository;
import com.imara.shipping.repository.ShipmentRepository;
import com.imara.shipping.repository.core.AbstractRepository;
import com.imara.shipping.service.core.AbstractService;
import com.imara.shipping.utility.Base64ToFileConverter;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class ApprovalService extends AbstractService<Approval> {
    @Autowired
    private ApprovalImageService approvalImageService;
    @Autowired
    private ApprovalRepository approvalRepository;

    @Autowired
    private ShipmentRepository shipmentRepository;


    @Override
    protected AbstractRepository getRepository() {
        return approvalRepository;
    }

    @Override
    protected Logger getLog() {
        return log;
    }

    @Override
    public Approval save(Approval approval) {


        long shipmentId = approval.getShipmentId();
        Approval existingApproval = approvalRepository.findByShipmentId(shipmentId);

        if (existingApproval == null) {

            Approval savedApproval = approvalRepository.save(approval);

            for (ApprovalImage image : approval.getImages()) {
                image.setApprovalId(savedApproval.getId());

//            // Save the image
//            String uploadDir = AppConstants.IMAGE_UPLOAD_DIR;
//
//            // Decode Base64 string to byte array
//            byte[] imageData = Base64.decodeBase64(image.getImage());
//
//            try {
//                Path uploadPath = Paths.get(uploadDir);
//
//                if (!Files.exists(uploadPath)) {
//                    try {
//                        Files.createDirectories(uploadPath);
//                    } catch (IOException e) {
//                        throw new RuntimeException(e);
//                    }
//                }
//
//                LocalDateTime now = LocalDateTime.now();
//                String formattedDateTime = now.format(DateTimeFormatter.ofPattern("yyyyMMddHHmmssSSS"));
//
//                //Set a name for the image
//                String fileName = "SHIPMENT-IMAGE-" + formattedDateTime + ".jpeg";
//
//                // Path to save the image file
//                Path filePath = uploadPath.resolve(fileName);
//
//                // Save the image file
//                Files.write(filePath, imageData, StandardOpenOption.CREATE);
//
//
//                image.setImage(AppConstants.IMAGE_UPLOAD_FOLDER_URL_PATH + fileName);
//                approvalImageService.save(image);
//
//            } catch (IOException e) {
//                e.printStackTrace();
//            }


                String imageUrl = Base64ToFileConverter.saveImage(image.getImage(), "jpeg", AppConstants.IMAGE_FILE_PREFIX);

                image.setImage(imageUrl);
                approvalImageService.save(image);
            }
            return savedApproval;
        } else {
            // Approval already exists, do not create a new one
            return existingApproval;
        }
    }


    public Approval findByShipmentId(long id) {
        return approvalRepository.findByShipmentId(id);
    }
}
