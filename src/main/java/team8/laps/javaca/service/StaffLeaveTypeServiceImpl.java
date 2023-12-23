package team8.laps.javaca.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import team8.laps.javaca.interfacemethods.StaffLeaveTypeService;
import team8.laps.javaca.model.Staff_Leave_Type;
import team8.laps.javaca.repository.StaffLeaveTypeRepository;


@Service
@Transactional
public class StaffLeaveTypeServiceImpl implements StaffLeaveTypeService{
	
	@Autowired
	private StaffLeaveTypeRepository staffLeaveTypeRepository;
	
	//Modify the holiday of the selected user Annual holiday
	@Override
	@Transactional
	public void updateEntitleLeave_Annual(Staff_Leave_Type updateEntitleLeave_Annual) {
		
        for (Staff_Leave_Type staff_Leave_Type : staffLeaveTypeRepository.updateEntitleLeave_Annual
        		(updateEntitleLeave_Annual.getLeavetype().getId(), 
        				updateEntitleLeave_Annual.getStaff().getDesignation())) {
        	staff_Leave_Type.setLeave_entitle(updateEntitleLeave_Annual.getLeave_entitle()); 
		  staffLeaveTypeRepository.save(staff_Leave_Type); 
        }
    }         

	//There just modify the holiday of all the staffs Medical holiday
	@Override
	@Transactional
	public void updateEntitleLeave_Medical(Staff_Leave_Type updateEntitleLeave_Medical) {
        for (Staff_Leave_Type staff_Leave_Type : staffLeaveTypeRepository.updateEntitleLeave_Medical
        		(updateEntitleLeave_Medical.getLeavetype().getId())) {
        	staff_Leave_Type.setLeave_entitle(updateEntitleLeave_Medical.getLeave_entitle()); 
		  staffLeaveTypeRepository.save(staff_Leave_Type); 
        }
	}


}
