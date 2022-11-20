package org.binar.movie.services;

import org.binar.movie.dto.TicketData;

public interface InvoiceService {
    public byte[] generateFile(TicketData data);
    public byte[] generateReports();
}
