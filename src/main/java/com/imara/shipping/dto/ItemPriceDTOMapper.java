package com.imara.shipping.dto;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.imara.shipping.dto.core.AbstractDTOMapper;
import com.imara.shipping.model.ItemPrice;
import com.imara.shipping.service.ShipmentItemService;
import com.imara.shipping.utility.TimeZoneConverter;
@Component
public class ItemPriceDTOMapper extends AbstractDTOMapper<ItemPrice,ItemPriceDTO> {
	
	@Autowired
	private TimeZoneConverter tzConverter;
	@Autowired
	private ShipmentItemService shipmentItemService;
	
	@Override
	public ItemPrice getEntity(ItemPriceDTO dto) {
		if (dto == null) return null;
		
		ItemPrice obj = new ItemPrice();
		obj.setId(dto.getId());
	    obj.setTimestampC(tzConverter.convertFromUTC(dto.getTimestampC()));
	    obj.setTimestampU(tzConverter.convertFromUTC(dto.getTimestampU()));
	    obj.setCreatedBy(dto.getCreatedBy());
	    obj.setUpdatedBy(dto.getUpdatedBy());
	    obj.setPrice(dto.getPrice());
	    obj.setShipmentItem(shipmentItemService.findById(dto.getShipmentItemId()));
		return obj;
	}

	@Override
	public ItemPriceDTO getDTO(ItemPrice obj, int format) {
		if (obj == null) {
            return null;
        }
		ItemPriceDTO dto = new ItemPriceDTO();
		dto.setId(obj.getId());
        dto.setCreatedBy(obj.getCreatedBy());
        dto.setUpdatedBy(obj.getUpdatedBy());
        dto.setTimestampC(obj.getTimestampC());
        dto.setTimestampU(obj.getTimestampU());
        dto.setPrice(obj.getPrice());
        dto.setShipmentItemId(obj.getShipmentItem().getId());
		return dto;
	}
}
