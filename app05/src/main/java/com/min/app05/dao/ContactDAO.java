package com.min.app05.dao;

import java.util.List;

import com.min.app05.dto.ContactDTO;

public interface ContactDAO {
  List<ContactDTO> getContactList();
  ContactDTO getContactByNo(int contactNo);
  int registerContact(ContactDTO contact);
  int modifyContact(ContactDTO contact);
  int removeContact(int contactNo);
}
