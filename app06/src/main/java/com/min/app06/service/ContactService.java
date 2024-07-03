package com.min.app06.service;

import java.util.List;

import com.min.app06.dto.ContactDTO;

public interface ContactService {
  List<ContactDTO> getContactList();
  ContactDTO getContactByNo(int contactNo);
  int tx_registerContact(ContactDTO contact);
  int tx_modifyContact(ContactDTO contact);
  int tx_removeContact(int contactNo);
  int tx_removeContactList(String[] contactNoList);
  void tx_txTest();
}
