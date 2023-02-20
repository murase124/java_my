package opaqueChange;

import javax.swing.JComponent;
import javax.swing.JPanel;

public class OpaqueChange {



	//全ての表示非表示制御
	
	boolean Opaque_flg = false;
	boolean name_flg = false;//true 名前がついているものには設定しない
	String names[] = null;
	
	public OpaqueChange(JPanel jpanel) {
		Opaques_panel(jpanel);
	}
	
	public OpaqueChange(JPanel jpanel , boolean Opaque_flg) {
		this.Opaque_flg = Opaque_flg;
		Opaques_panel(jpanel);
	}
	public OpaqueChange(JPanel jpanel, String names[]) {
		this.names = names;
		Opaques_panel(jpanel);
	}
	public OpaqueChange(JPanel jpanel , boolean Opaque_flg, String names[]) {
		this.Opaque_flg = Opaque_flg;
		this.names = names;
		Opaques_panel(jpanel);
	}
	public OpaqueChange(JPanel jpanel , boolean Opaque_flg, boolean name_flg) {
		this.Opaque_flg = Opaque_flg;
		this.name_flg = name_flg;
		Opaques_panel(jpanel);
	}
	
	//パネル
	public void Opaques_panel(JPanel jpanel) {
		if(jpanel.getComponentCount() != 0) {
			for(int i =0; i < jpanel.getComponentCount();i++) {
				if(jpanel.getComponent(i).getClass().getName().equals("javax.swing.JPanel")) {
					Opaques_panel((JPanel) jpanel.getComponent(i));
				}else {
					Opaques_no_panel((JComponent) jpanel.getComponent(i));
				}
			}
			if(!name_flg && names == null || jpanel.getName() == null) {
				jpanel.setOpaque(Opaque_flg);
			}else if(names != null) {
				boolean flg = true;
				for (String string : names) {
					if(string.equals(jpanel.getName())) flg = false;
				}
				if(flg) jpanel.setOpaque(Opaque_flg);
			}
		}
		return;
	}
	
	//パネル以外　
	public void Opaques_no_panel(JComponent jpanel ) {
		if(!name_flg && names == null || jpanel.getName() == null) {
			jpanel.setOpaque(Opaque_flg);
		}else if(names != null) {
			boolean flg = true;
			for (String string : names) {
				if(string.equals(jpanel.getName())) flg = false;
			}
			if(flg) jpanel.setOpaque(Opaque_flg);

		}
		return;
	}
}


