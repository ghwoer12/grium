package com.project.four.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.four.model.dto.CondolanceDto;
import com.project.four.model.dto.FamilyDto;
import com.project.four.model.dto.FuneralDto;
import com.project.four.model.dto.GoneDto;
import com.project.four.model.dto.ProcedureDto;
import com.project.four.model.repo.GoneRepository;

@Service
public class GoneServiceImpl implements GoneService {

	@Autowired
	GoneRepository grepo;
	
	@Override
	public List<GoneDto> getList() throws Exception {
		// TODO Auto-generated method stub
		return grepo.getList();
	}

	@Override
	public GoneDto getgone(String gone_id) throws Exception {
		// TODO Auto-generated method stub
		return grepo.callgone(gone_id);
	}
	
	@Override
	public FamilyDto getfamily(String gone_id) throws Exception {
		// TODO Auto-generated method stub
		return grepo.callfamily(gone_id);
	}
	
	@Override
	public ProcedureDto getprocedure(String gone_id) throws Exception {
		// TODO Auto-generated method stub
		return grepo.callprocedure(gone_id);
	}
	
	@Override
	public CondolanceDto getcondolance(String gone_id) throws Exception {
		// TODO Auto-generated method stub
		return grepo.callcondolance(gone_id);
	}
	
	@Override
	public FuneralDto getfuneral(String gone_id) throws Exception {
		// TODO Auto-generated method stub
		return grepo.callfuneral(gone_id);
	}
}
