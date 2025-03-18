package com.poc.kafkapreformace.infrastructure.in.rest;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.archetype.base.core.exception.FunctionalException;
import com.archetype.base.core.utils.PaginationUtil;
import com.poc.kafkapreformace.application.PrototypeService;
import com.poc.kafkapreformace.domain.Prototype;
import com.poc.kafkapreformace.domain.dto.PrototypeRequest;
import com.poc.kafkapreformace.domain.dto.PrototypeResponse;
import com.poc.kafkapreformace.domain.mapper.PrototypeMapperVo;
import com.poc.kafkapreformace.infrastructure.producer.PerformanceProducer;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping(path = "/v1/prototype")
public class PrototypeController implements IPrototypeController { 
	
	@Autowired
	private PrototypeService service;
	
	@Autowired
	private PrototypeMapperVo mapperVo;

	@GetMapping("/test")
	public ResponseEntity<String> test(){
		return ResponseEntity.ok("The application is working");
	}

	@GetMapping()
	public ResponseEntity<List<PrototypeResponse>> getAll(){
		return ResponseEntity.ok(
				service
					.get()
					.stream()
					.map(mapperVo::toResponse)
					.collect(Collectors.toList())
					);
	}

	@GetMapping("/{id}")
	public ResponseEntity<PrototypeResponse> findById(@PathVariable Long id){
		return ResponseEntity.ok(mapperVo.toResponse(service.get(id)));
	}

	@PostMapping()
	public ResponseEntity<PrototypeResponse> post(@RequestBody PrototypeRequest data){

		return ResponseEntity.ok(
				mapperVo.toResponse(
						service.save(mapperVo.toDomain(data))
						)
				);
	}

	@PutMapping("/{id}")
	public ResponseEntity<PrototypeResponse> put(@PathVariable Long id, @RequestBody PrototypeRequest data)
			throws FunctionalException {
		return ResponseEntity.ok(
				mapperVo.toResponse(
						service.update(id,mapperVo.toDomain(data))
						)
				);
	}

	@GetMapping("/findall")
	public ResponseEntity<List<PrototypeResponse>> findAllPage(Pageable pageable) {
		Page<Prototype> page = service.findAllPage(PaginationUtil.parsePageableSort(Prototype.class, pageable));
		HttpHeaders headers =PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
		return new ResponseEntity<>( mapperVo.toResponse(page.getContent()) ,headers, HttpStatus.OK);
	}

	@Autowired
	private PerformanceProducer producer;
	
	@GetMapping("/kafkatest/{repeat}")
	public ResponseEntity<String> kafkatest(@PathVariable Long repeat) {
		for(int i=0;i<repeat;i++)
			producer.produceASynCall("prototype4j-topic" ,"key1",String.valueOf(i));

		return new ResponseEntity<>( "successful",HttpStatus.OK);
		
	}
	@GetMapping("/kafkatestsyn/{repeat}")
	public ResponseEntity<String> kafkatestSyn(@PathVariable Long repeat) {
//		producer.produceASyn("${spring.kafka.template.default-topic}", "key1", "mesaje_prueba");
//		long inicio=System.currentTimeMillis();
//		log.info("Inicio: {}",inicio);
		for(int i=0;i<repeat;i++)
			producer.produceSyn("prototype4j-topic" , "key1",String.valueOf(i));
//		log.info("fin: {}",System.currentTimeMillis()-inicio);

		return new ResponseEntity<>( "successful",HttpStatus.OK);
		
	}
}
