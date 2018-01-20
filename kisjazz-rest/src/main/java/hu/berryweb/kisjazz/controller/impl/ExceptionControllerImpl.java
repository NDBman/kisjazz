package hu.berryweb.kisjazz.controller.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;

import hu.berryweb.kisjazz.controller.IExceptionController;

@ControllerAdvice
public class ExceptionControllerImpl implements IExceptionController {

	private final Logger LOG = LoggerFactory.getLogger(ExceptionControllerImpl.class);
	
	@Override
	public void handleBadRequest(Exception e) {
		LOG.debug("Bad request. Message: " + e.getMessage());
	}

	@Override
	public void handleConflict(Exception e) {
		LOG.debug("Conflict. Message: " + e.getMessage());
	}

}
