/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CalorieTracker;

import javax.swing.JOptionPane;

/**
 *
 * @author Pippin
 */
public class Functions
{
    public static void MessageBox(String _passedString, String _passedTitle)
    {
        JOptionPane.showMessageDialog(null, _passedString, _passedTitle, 1);
    }
    
    public static void DebugPrint(String _passedString)
    {
        System.out.println(_passedString);
    }
}
