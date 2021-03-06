package org.multibit.viewsystem.swing.view;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.Action;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import org.multibit.controller.MultiBitController;
import org.multibit.model.AddressBookData;
import org.multibit.model.DataProvider;
import org.multibit.model.MultiBitModel;
import org.multibit.viewsystem.View;
import org.multibit.viewsystem.swing.MultiBitFrame;
import org.multibit.viewsystem.swing.action.CopySendAddressAction;
import org.multibit.viewsystem.swing.action.CreateNewSendingAddressAction;
import org.multibit.viewsystem.swing.action.PasteAddressAction;
import org.multibit.viewsystem.swing.action.SendBitcoinConfirmAction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SendBitcoinPanel extends AbstractTradePanel implements DataProvider, View {

    private static final long serialVersionUID = -2065108865497111662L;

    private final Logger log = LoggerFactory.getLogger(SendBitcoinPanel.class);

    private static final String SEND_BITCOIN_BIG_ICON_FILE = "/images/send-big.jpg";
    
    private JButton pasteAddressButton;
    private JButton sendButton;

    public SendBitcoinPanel(MultiBitFrame mainFrame, MultiBitController controller) {
        super(mainFrame, controller);
    }

    @Override
    protected boolean isReceiveBitcoin() {
        return false;
    }
   
    @Override
    protected Action getCreateNewAddressAction() {
        return new CreateNewSendingAddressAction(controller, this);
    }
    
    @Override
    protected String getAddressConstant() {
        return MultiBitModel.SEND_ADDRESS;
    }
    
    @Override
    protected String getLabelConstant() {
        return MultiBitModel.SEND_LABEL;
    }
    @Override
    protected String getAmountConstant() {
        return MultiBitModel.SEND_AMOUNT;
    }

    @Override
    protected String getUriImageConstant() {
        return MultiBitModel.SEND_URI_IMAGE;
    }

    /**
     * method for concrete impls to populate the localisation map
     */
    @Override
    protected void populateLocalisationMap() {
        localisationKeyConstantToKeyMap.put(ADDRESSES_TITLE, "sendBitcoinPanel.sendingAddressesTitle");                
        localisationKeyConstantToKeyMap.put(CREATE_NEW_TOOLTIP, "createOrEditAddressAction.createSending.tooltip");       
    }

    protected JPanel createFormPanel() {
        formPanel = new JPanel();
        formPanel.setBorder(new DashedBorder(controller.getLocaliser().getLocale()));
        formPanel.setBackground(MultiBitFrame.VERY_LIGHT_BACKGROUND_COLOR);

        JPanel buttonPanel = new JPanel();
        FlowLayout flowLayout = new FlowLayout();
        flowLayout.setAlignment(FlowLayout.LEADING);
        buttonPanel.setLayout(flowLayout);

        formPanel.setLayout(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();

        JPanel filler1 = new JPanel();
        filler1.setOpaque(false);
        constraints.fill = GridBagConstraints.BOTH;
        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.weightx = 5;
        constraints.weighty = 0.10;
        constraints.anchor = GridBagConstraints.LINE_START;
        formPanel.add(filler1, constraints);

        ImageIcon bigIcon = MultiBitFrame.createImageIcon(SEND_BITCOIN_BIG_ICON_FILE);
        constraints.fill = GridBagConstraints.BOTH;
        constraints.gridx = 1;
        constraints.gridy = 1;
        constraints.weightx = 0.3;
        constraints.weighty = 0.08;
        constraints.gridwidth = 1;
        constraints.anchor = GridBagConstraints.CENTER;
        formPanel.add(new JLabel(bigIcon), constraints);

        JLabel helpLabel1 = new JLabel(controller.getLocaliser().getString("sendBitcoinPanel.helpLabel1.message"));
        helpLabel1.setHorizontalAlignment(JLabel.LEADING);
        constraints.fill = GridBagConstraints.NONE;
        constraints.gridx = 3;
        constraints.gridy = 1;
        constraints.weightx = 0.3;
        constraints.weighty = 0.08;
        constraints.gridwidth = 4;
        constraints.anchor = GridBagConstraints.LINE_START;
        formPanel.add(helpLabel1, constraints);

        JLabel helpLabel2 = new JLabel(controller.getLocaliser().getString("sendBitcoinPanel.helpLabel2.message"));
        helpLabel2.setHorizontalAlignment(JLabel.LEADING);
        constraints.fill = GridBagConstraints.NONE;
        constraints.gridx = 3;
        constraints.gridy = 2;
        constraints.weightx = 0.3;
        constraints.weighty = 0.08;
        constraints.gridwidth = 4;
        constraints.anchor = GridBagConstraints.LINE_START;
        formPanel.add(helpLabel2, constraints);

        JLabel helpLabel3 = new JLabel(controller.getLocaliser().getString("sendBitcoinPanel.helpLabel3.message"));
        helpLabel3.setHorizontalAlignment(JLabel.LEADING);
        constraints.fill = GridBagConstraints.NONE;
        constraints.gridx = 3;
        constraints.gridy = 3;
        constraints.weightx = 0.3;
        constraints.weighty = 0.08;
        constraints.gridwidth = 4;
        constraints.anchor = GridBagConstraints.LINE_START;
        formPanel.add(helpLabel3, constraints);

        JPanel filler2 = new JPanel();
        filler2.setOpaque(false);
        constraints.fill = GridBagConstraints.NONE;
        constraints.gridx = 3;
        constraints.gridy = 0;
        constraints.weightx = 0.05;
        constraints.gridwidth = 1;
        constraints.anchor = GridBagConstraints.LINE_START;
        formPanel.add(filler2, constraints);

        JLabel addressLabel = new JLabel(controller.getLocaliser().getString("sendBitcoinPanel.addressLabel"));
        addressLabel.setToolTipText(controller.getLocaliser().getString("sendBitcoinPanel.addressLabel.tooltip"));
        addressLabel.setHorizontalAlignment(JLabel.TRAILING);
        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.gridx = 1;
        constraints.gridy = 4;
        constraints.weightx = 4.0;
        constraints.weighty = 0.15;
        constraints.gridwidth = 1;
        constraints.anchor = GridBagConstraints.LINE_END;
        formPanel.add(addressLabel, constraints);

        JLabel filler4 = new JLabel("");
        filler4.setOpaque(false);
        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.gridx = 2;
        constraints.gridy = 4;
        constraints.weightx = 1;
        constraints.weighty = 0.5;
        constraints.anchor = GridBagConstraints.LINE_START;
        formPanel.add(filler4, constraints);

        addressTextField = new JTextField(35);
        addressTextField.setHorizontalAlignment(JTextField.LEADING);
        addressTextField.setMinimumSize(new Dimension(MultiBitFrame.WIDTH_OF_LONG_FIELDS, 24));
        addressTextField.setMaximumSize(new Dimension(MultiBitFrame.WIDTH_OF_LONG_FIELDS, 24));

        addressTextField.addKeyListener(new QRCodeKeyListener());
        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.gridx = 3;
        constraints.gridy = 4;
        constraints.weightx = 0.1;
        constraints.gridwidth = 2;
        constraints.anchor = GridBagConstraints.LINE_START;
        formPanel.add(addressTextField, constraints);

        ImageIcon copyIcon = MultiBitFrame.createImageIcon(MultiBitFrame.COPY_ICON_FILE);
        CopySendAddressAction copyAddressAction = new CopySendAddressAction(controller, this, copyIcon);
        JButton copyAddressButton = new JButton(copyAddressAction);
        constraints.fill = GridBagConstraints.NONE;
        constraints.gridx = 6;
        constraints.gridy = 4;
        constraints.weightx = 1;
        constraints.gridwidth = 1;
        constraints.anchor = GridBagConstraints.LINE_START;
        formPanel.add(copyAddressButton, constraints);

        ImageIcon pasteIcon = MultiBitFrame.createImageIcon(MultiBitFrame.PASTE_ICON_FILE);
        PasteAddressAction pasteAddressAction = new PasteAddressAction(controller, this, pasteIcon);
        pasteAddressButton = new JButton(pasteAddressAction);
        constraints.fill = GridBagConstraints.NONE;
        constraints.gridx = 7;
        constraints.gridy = 4;
        constraints.weightx = 3;
        constraints.gridwidth = 1;
        constraints.anchor = GridBagConstraints.LINE_START;
        formPanel.add(pasteAddressButton, constraints);

        JLabel labelLabel = new JLabel(controller.getLocaliser().getString("sendBitcoinPanel.labelLabel"));
        labelLabel.setBorder(BorderFactory.createMatteBorder(4, 0, 0, 0, MultiBitFrame.VERY_LIGHT_BACKGROUND_COLOR));
        labelLabel.setToolTipText(controller.getLocaliser().getString("sendBitcoinPanel.labelLabel.tooltip"));
        labelLabel.setHorizontalAlignment(JLabel.TRAILING);
        constraints.fill = GridBagConstraints.NONE;
        constraints.gridx = 1;
        constraints.gridy = 5;
        constraints.weightx = 0.3;
        constraints.weighty = 0.15;
        constraints.gridwidth = 1;
        constraints.anchor = GridBagConstraints.ABOVE_BASELINE_TRAILING;
        formPanel.add(labelLabel, constraints);

        JTextField aTextField = new JTextField();
        labelTextArea = new JTextArea("", 2, 20);
        labelTextArea.setBorder(aTextField.getBorder());
        labelTextArea.addKeyListener(new QRCodeKeyListener());

        JScrollPane labelScrollPane = new JScrollPane(labelTextArea, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
                JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        labelScrollPane.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, MultiBitFrame.DARK_BACKGROUND_COLOR));
        labelScrollPane.setOpaque(true);
        labelScrollPane.setBackground(MultiBitFrame.VERY_LIGHT_BACKGROUND_COLOR);
        constraints.fill = GridBagConstraints.BOTH;
        constraints.gridx = 3;
        constraints.gridy = 5;
        constraints.weightx = 0.15;
        constraints.weighty = 0.40;
        constraints.gridwidth = 2;
        constraints.anchor = GridBagConstraints.LINE_START;
        formPanel.add(labelScrollPane, constraints);

        JPanel filler5 = new JPanel();
        filler5.setOpaque(false);
        constraints.fill = GridBagConstraints.BOTH;
        constraints.gridx = 5;
        constraints.gridy = 5;
        constraints.weightx = 1;
        constraints.weighty = 0.4;
        constraints.gridwidth = 1;
        constraints.anchor = GridBagConstraints.LINE_START;
        formPanel.add(filler5, constraints);

        JLabel amountLabel = new JLabel(controller.getLocaliser().getString("sendBitcoinPanel.amountLabel"));
        amountLabel.setToolTipText(controller.getLocaliser().getString("sendBitcoinPanel.amountLabel.tooltip"));
        amountLabel.setHorizontalAlignment(JLabel.TRAILING);
        constraints.fill = GridBagConstraints.NONE;
        constraints.gridx = 1;
        constraints.gridy = 6;
        constraints.gridwidth = 1;
        constraints.weightx = 0.3;
        constraints.weighty = 0.30;
        constraints.anchor = GridBagConstraints.LINE_END;
        formPanel.add(amountLabel, constraints);

        amountTextField = new JTextField("", 20);
        amountTextField.setHorizontalAlignment(JTextField.TRAILING);
        amountTextField.setMinimumSize(new Dimension(MultiBitFrame.WIDTH_OF_AMOUNT_FIELD, 24));
        amountTextField.setMaximumSize(new Dimension(MultiBitFrame.WIDTH_OF_AMOUNT_FIELD, 24));
        amountTextField.addKeyListener(new QRCodeKeyListener());

        constraints.fill = GridBagConstraints.NONE;
        constraints.gridx = 3;
        constraints.gridy = 6;
        constraints.weightx = 0.1;
        constraints.weighty = 0.5;
        constraints.anchor = GridBagConstraints.LINE_START;
        formPanel.add(amountTextField, constraints);

        JLabel amountUnitLabel = new JLabel(controller.getLocaliser().getString("sendBitcoinPanel.amountUnitLabel"));
        amountUnitLabel.setToolTipText(controller.getLocaliser().getString("sendBitcoinPanel.amountUnitLabel.tooltip"));
        constraints.gridx = 4;
        constraints.gridy = 6;
        constraints.weightx = 2.0;
        constraints.weighty = 0.30;
        constraints.anchor = GridBagConstraints.LINE_START;
        formPanel.add(amountUnitLabel, constraints);

        SendBitcoinConfirmAction sendBitcoinConfirmAction = new SendBitcoinConfirmAction(controller, this);
        sendButton = new JButton(sendBitcoinConfirmAction);
        constraints.fill = GridBagConstraints.NONE;
        constraints.gridx = 6;
        constraints.gridy = 6;
        constraints.weightx = 10;
        constraints.weighty = 0.4;
        constraints.gridwidth = 2;
        constraints.anchor = GridBagConstraints.LINE_START;
        formPanel.add(sendButton, constraints);

        return formPanel;
    }

    public void loadForm() {
        // get the current address, label and amount from the model
        String address = controller.getModel().getActiveWalletPreference(MultiBitModel.SEND_ADDRESS);
        String label = controller.getModel().getActiveWalletPreference(MultiBitModel.SEND_LABEL);
        String amount = controller.getModel().getActiveWalletPreference(MultiBitModel.SEND_AMOUNT);

        if (address != null) {
            addressTextField.setText(address);
        }
        if (label != null) {
            labelTextArea.setText(label);
        }
        if (amount != null) {
            amountTextField.setText(amount);
        }
    }

    public void setAddressBookDataByRow(AddressBookData addressBookData) {
        addressTextField.setText(addressBookData.getAddress());
        addressesTableModel.setAddressBookDataByRow(addressBookData, selectedAddressRow, false);
    }

    @Override
    public void displayView() {
        super.displayView();
        updateView();
    }

    @Override
    public void updateView() {
        super.updateView();
        // disable any new changes if another process has changed the wallet
        if (controller.getModel().getActivePerWalletModelData() != null
                && controller.getModel().getActivePerWalletModelData().isFilesHaveBeenChangedByAnotherProcess()) {
            // files have been changed by another process - disallow edits
            mainFrame.setUpdatesStoppedTooltip(addressTextField);
            addressTextField.setEditable(false);
            addressTextField.setEnabled(false);

            if (sendButton != null) {
                sendButton.setEnabled(false);
                mainFrame.setUpdatesStoppedTooltip(sendButton);
            }
            if (pasteAddressButton != null) {
                pasteAddressButton.setEnabled(false);
                mainFrame.setUpdatesStoppedTooltip(pasteAddressButton);
            }
            titleLabel.setText(controller.getLocaliser().getString("sendBitcoinPanel.sendingAddressesTitle.mayBeOutOfDate"));
            mainFrame.setUpdatesStoppedTooltip(titleLabel);                 
        } else {
            addressTextField.setToolTipText(null);
            addressTextField.setEditable(true);
            addressTextField.setEnabled(true);

            if (sendButton != null) {
                sendButton.setEnabled(true);
                sendButton.setToolTipText(controller.getLocaliser().getString("sendBitcoinAction.tooltip"));
            }
            if (pasteAddressButton != null) {
                pasteAddressButton.setEnabled(true);
                pasteAddressButton.setToolTipText(controller.getLocaliser().getString("pasteAddressAction.tooltip"));
            }
            titleLabel.setText(controller.getLocaliser().getString("sendBitcoinPanel.sendingAddressesTitle"));
            titleLabel.setToolTipText(null);
        }
    }
}
