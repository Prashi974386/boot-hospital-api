package com.ty.hospitalapi.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.ty.hospitalapi.dao.ObservationDao;
import com.ty.hospitalapi.dto.Item;
import com.ty.hospitalapi.dto.MedOrder;
import com.ty.hospitalapi.dto.Observation;
import com.ty.hospitalapi.dto.ResponseStracture;
import com.ty.hospitalapi.exception.NoIDFoundException;

@Service
public class ObservationService {
	@Autowired
	ObservationDao observationDao;

	public ResponseStracture<Observation> saveObservation(int encounter_id, Observation observation) {
		ResponseStracture<Observation> responseStracture = new ResponseStracture<Observation>();
		List<MedOrder> medOrders = observation.getMedOrder();
		if (medOrders.isEmpty()) {
			return null;
		} else {
			for (MedOrder medOrder : medOrders) {
				double result = 0;
				medOrder.setObservation(observation);
				List<Item> items = medOrder.getItem();
				for (Item item : items) {
					double result1 = item.getCost()*item.getQuantity();
					result += result1;
					item.setMedOrder(medOrder);
				}
				medOrder.setTotal(result);
			}
		}
		responseStracture.setStatusCode(HttpStatus.ACCEPTED.value());
		responseStracture.setMessage("Observation Data is saved Successfully");
		responseStracture.setData(observationDao.saveObservation(encounter_id, observation));
		return responseStracture;
	}

	public ResponseStracture<List<Observation>> getAllObservation() {
		ResponseStracture<List<Observation>> responseStracture = new ResponseStracture<List<Observation>>();
		List<Observation> observations = observationDao.geAllObservation();
		if (observations != null) {
			responseStracture.setStatusCode(HttpStatus.FOUND.value());
			responseStracture.setMessage("All Observation Details");
			responseStracture.setData(observations);
		} else {
			responseStracture.setStatusCode(HttpStatus.NOT_FOUND.value());
			responseStracture.setMessage("Observation Data is Not exist");
			responseStracture.setData(null);
		}
		return responseStracture;
	}

	public ResponseStracture<Observation> getObsevationById(int id) {
		ResponseStracture<Observation> responseStracture = new ResponseStracture<Observation>();
		Observation observation = observationDao.getObservationById(id);
		if (observation != null) {
			responseStracture.setStatusCode(HttpStatus.FOUND.value());
			responseStracture.setMessage("Observation Details By Id");
			responseStracture.setData(observation);
		} else {
			throw new NoIDFoundException("Observation ID " + id + " does not exist");
		}
		return responseStracture;
	}

	public ResponseStracture<String> deleteObservation(int id) {
		ResponseStracture<String> responseStracture = new ResponseStracture<String>();
		Observation observation = observationDao.getObservationById(id);
		if (observation != null) {
			responseStracture.setStatusCode(HttpStatus.FOUND.value());
			responseStracture.setMessage("Observation Data Deleted By Id");
			responseStracture.setData(observationDao.deleteObservation(id));
		} else {
			throw new NoIDFoundException("Observation ID " + id + " does not exist");
		}
		return responseStracture;
	}

	public ResponseStracture<Observation> updateObservation(int encounter_id, Observation observation) {
		ResponseStracture<Observation> responseStracture = new ResponseStracture<Observation>();
		Observation observations = observationDao.getObservationById(observation.getId());
		if (observations != null) {
			List<MedOrder> medOrders = observation.getMedOrder();
			if (medOrders.isEmpty()) {
				return null;
			} else {
				for (MedOrder medOrder : medOrders) {
					medOrder.setObservation(observation);
					List<Item> items = medOrder.getItem();
					for (Item item : items) {
						item.setMedOrder(medOrder);
					}
				}
			}
			responseStracture.setStatusCode(HttpStatus.OK.value());
			responseStracture.setMessage("Observation Data Deleted By Id");
			responseStracture.setData(observationDao.updateObservation(encounter_id, observation));
		} else {
			throw new NoIDFoundException("Observation ID " + observation.getId() + " does not exist");
		}
		return responseStracture;
	}
}
