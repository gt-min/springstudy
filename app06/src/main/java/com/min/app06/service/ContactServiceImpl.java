package com.min.app06.service;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.min.app06.dto.ContactDTO;
import com.min.app06.mapper.ContactMapper;

@Service
public class ContactServiceImpl implements ContactService {

  private ContactMapper contactMapper;
  
  @Autowired  
  public ContactServiceImpl(ContactMapper contactMapper) {
    super();
    this.contactMapper = contactMapper;
  }

  @Override
  public List<ContactDTO> getContactList() {
    return contactMapper.getContactList();
  }

  @Override
  public ContactDTO getContactByNo(int contactNo) {
    return contactMapper.getContactByNo(contactNo);
  }

  @Override
  public int registerContact(ContactDTO contact) {
    return contactMapper.registerContact(contact);
  }

  @Override
  public int modifyContact(ContactDTO contact) {
    return contactMapper.modifyContact(contact);
  }

  @Override
  public int removeContact(int contactNo) {
    return contactMapper.removeContact(contactNo);
  }
  
  @Override
  public int removeContactList(String[] contactNoList) {
    return contactMapper.removeContactList(Arrays.asList(contactNoList));
  }

}