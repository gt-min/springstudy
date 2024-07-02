package com.min.app06.service;

import java.util.List;

import com.min.app06.dto.ContactDTO;

public interface ContactService {
  List<ContactDTO> getContactList();
  ContactDTO getContactByNo(int contactNo);
  int registerContact(ContactDTO contact);
  int modifyContact(ContactDTO contact);
  int removeContact(int contactNo);
  int removeContactList(String[] contactNoList);
}
