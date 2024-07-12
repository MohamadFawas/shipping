package com.imara.shipping.controller;

import java.util.List;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.imara.shipping.controller.core.AbstractController;
import com.imara.shipping.controller.core.Result;
import com.imara.shipping.dto.ItemPriceDTO;
import com.imara.shipping.dto.ItemPriceDTOMapper;
import com.imara.shipping.model.ItemPrice;
import com.imara.shipping.model.ShipmentItem;
import com.imara.shipping.service.ItemPriceService;
import com.imara.shipping.service.core.AbstractService;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/api/ItemPrice/")
@Slf4j
@Transactional
public class ItemPriceController  extends AbstractController<ItemPrice,ItemPriceDTO> {
	@Autowired
	private ItemPriceService itemPriceService;
	
	@Autowired
	private ItemPriceDTOMapper itemPriceDTOMapper;
	
	@Override
	protected AbstractService getService() {
		return itemPriceService;
	}

	@Override
	protected Logger getLog() {
		return log;
	}
	protected ItemPriceDTOMapper getDTOMapper() {
		return itemPriceDTOMapper;
		
	}
	
	@PostMapping("save")
	public Result<ItemPriceDTO> save(@RequestBody  ItemPriceDTO itemPriceDTO){
		if ((itemPriceService.isItemPriceExists(itemPriceDTO.getShipmentItemId()))) {
			log.info("itemPrice already exits");
			return null;
		}
		
		ItemPrice obj = new ItemPrice();
		ShipmentItem shipmentItem = new ShipmentItem();
		shipmentItem.setId(itemPriceDTO.getShipmentItemId());
		obj.setShipmentItem(shipmentItem);
		return super.save(itemPriceDTO);
		
	}
		
	@GetMapping("get_all")
	public Result<List<ItemPriceDTO>> getAll(){
		return super.getAll();
		
	}
	
	@DeleteMapping("delete")
	public Result deleteById(@RequestParam("id") long id) {
        return super.deleteById(id);
    }
}