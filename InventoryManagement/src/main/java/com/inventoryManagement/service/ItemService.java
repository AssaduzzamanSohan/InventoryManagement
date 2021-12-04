package com.inventoryManagement.service;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.inventoryManagement.constants.ActionType;
import com.inventoryManagement.entity.Items;
import com.inventoryManagement.repository.ItemsRepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class ItemService {
	@Autowired
	private ItemsRepository itemRepository;

	/**
	 * Developer will just call this method with information in item object and what
	 * to do in actionType
	 * 
	 * @param item
	 * @param actionType
	 * @return List<Items>
	 * @throws Exception
	 */
	public List<Items> handleSingleRequest(Items item, String actionType) throws Exception {
		if (actionType.matches(ActionType.SELECT.toString())) {
			return select(item);
		} else if (actionType.matches(ActionType.SELECT_ALL.toString())) {
			return selectAll();
		} else if (actionType.matches(ActionType.NEW.toString()) || actionType.matches(ActionType.SAVE.toString())) {
			return save(item);
		} else if (actionType.matches(ActionType.UPDATE.toString())) {
			return update(item);
		} else if (actionType.matches(ActionType.DELETE.toString())) {
			return delete(item);
		} else {
			throw new Exception("Unknown action " + actionType);
		}
	}

	private List<Items> select(Items item) throws Exception {
		try {
			return itemRepository.findById(item.getId());
		} catch (Exception e) {
			log.error("Exception selecting Items [{}]", item.toString(), e);
			throw new Exception(e.getLocalizedMessage());
		}
	}

	private List<Items> selectAll() throws Exception {
		try {
			return itemRepository.findAll(new Sort(Sort.Direction.DESC, "entryDate"));
		} catch (Exception e) {
			log.error("Exception selecting all Items [{}]", e);
			throw new Exception(e.getLocalizedMessage());
		}
	}

	private List<Items> save(Items item) throws Exception {
		try {
			return Arrays.asList(itemRepository.save(item));
		} catch (Exception e) {
			log.error("Exception saving Items [{}]", item.toString(), e);
			throw new Exception(e.getLocalizedMessage());
		}
	}

	private List<Items> update(Items item) throws Exception {
		try {
			if (item.getId() == 0) {
				throw new Exception("Items id not found in Object, First Find then try to update");
			}

			return Arrays.asList(itemRepository.save(getUpdateableItems(item)));
		} catch (Exception e) {
			log.error("Exception updating Items [{}]", item.toString(), e);
			throw new Exception(e.getLocalizedMessage());
		}
	}

	private Items getUpdateableItems(Items item) throws Exception {
		List<Items> itemDbList = select(item);

		Items itemDb = itemDbList.get(0);
		if (item.getItemName() != null && !item.getItemName().isEmpty()) {
			itemDb.setItemName(item.getItemName());
		}
		return itemDb;
	}

	private List<Items> delete(Items item) throws Exception {
		try {
			itemRepository.delete(item);
			return Arrays.asList(item);
		} catch (Exception e) {
			log.error("Exception deleting Items [{}]", item.toString(), e);
			throw new Exception(e.getLocalizedMessage());
		}
	}
}
