package com.simulator.repository.atm;

import com.simulator.entities.atm.NdcSnCameraCtrl;
import com.simulator.entities.atm.NdcSnCameraCtrlId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NdcSnCameraCtrlRepository extends JpaRepository<NdcSnCameraCtrl, NdcSnCameraCtrlId> {
}