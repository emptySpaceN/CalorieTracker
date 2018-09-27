/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CalorieTracker;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridBagLayout;
import java.awt.Rectangle;
import java.awt.Stroke;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author Pippin
 */
public class CalendarDay extends JPanel
{
    // Classes
    private MainMenu MainMenuLayout;
    private DayOverview DayOverviewLayout;

    private JLabel DayText = new JLabel();

    private boolean isCurrentDay = false;

    public void setIsCurrentDay()
    {
        isCurrentDay = true;
        revalidate();
        repaint();
    }

    public boolean getIsCurrentDay()
    {
        return isCurrentDay;
    }

    private boolean hasFoodInDatabase = false;

    public boolean getHasFoodInDatabase()
    {
        return hasFoodInDatabase;
    }

    public void setFoodInDatabase(boolean _passedState)
    {
        hasFoodInDatabase = _passedState;
        revalidate();
        repaint();
    }

        
    private int year;

    public int getYear()
    {
        return year;
    }

    public void setYear(int _passedYear)
    {
        year = _passedYear;
    }

    private int month;

    public int getMonth()
    {
        return month;
    }

    public void setMonth(int _passedMonth)
    {
        month = _passedMonth;
    }

    private int selectionIndex = 0;

    public int getSelectionIndex()
    {
        return selectionIndex;
    }

    public void setSelectionIndex(int _passedIndex)
    {
        selectionIndex = _passedIndex;
    }

    public int getDisplayedDay()
    {
        return Integer.parseInt(DayText.getText());
    }

    public void setDisplayedDay(int _passedDayText)
    {
        if (_passedDayText != -1)
        {
            DayText.setText(Integer.toString(_passedDayText));
        } else
        {
            DayText.setText("");
        }
        DayText.setLocation((getWidth() - DayText.getWidth()) / 2, (getHeight() - DayText.getHeight()) / 2);
    }

    public Color getDayForecolor()
    {
        return DayText.getForeground();
    }

    public void setDayForecolor(Color _passedColor)
    {
        DayText.setForeground(_passedColor);
    }

    private enum PaintingState
    {
        mouseOver,
        mouseLeft,
        mouseDown,
        mouseUp,
        clearSelection
    }

    private PaintingState finalState = PaintingState.mouseLeft;

    public CalendarDay(MainMenu _passedMainMenu, int _passedDay)
    {
        MainMenuLayout = _passedMainMenu;

        add(DayText);
        setOpaque(false);
        setSize(50, 50);
        setLayout(new GridBagLayout());

        DayText.setFont(new Font("Microsoft Sans Serif", 0, 15));
        DayText.setText(Integer.toString(_passedDay));

        //DayText.Left = (this.Width - DayText.Width) / 2;
        //DayText.Top = (this.Height - DayText.Height) / 2;
        addMouseListener(new MouseListener()
        {

            public void mouseClicked(MouseEvent e)
            {
                if (e.getClickCount() == 1)
                {
                    if (e.getButton() == 1)
                    {
                        finalState = CalendarDay.PaintingState.mouseDown;

                        repaint();

                        MainMenuLayout.setLastSelectedDayIndex(selectionIndex);
                    }

                    //DaySurface.DisplayNutrition();
                } else if (e.getClickCount() == 2)
                {

                    //DayOverviewLayout = new DayOverview();
                    DayOverviewLayout.setModal(true);

                    DayOverviewLayout.setTitle("Tagesübersicht");

                    DayOverviewLayout.setVisible(true);
                }
            }

            public void mousePressed(MouseEvent e)
            {
            }

            public void mouseReleased(MouseEvent e)
            {
                if (e.getButton() == 1)
                {
                    finalState = CalendarDay.PaintingState.mouseUp;
                    MainMenuLayout.ResetSelection();
                    repaint();
                }
            }

            public void mouseEntered(MouseEvent e)
            {
                finalState = CalendarDay.PaintingState.mouseOver;
                repaint();
            }

            public void mouseExited(MouseEvent e)
            {
                finalState = CalendarDay.PaintingState.mouseLeft;
                repaint();
            }

        });
    }

//    private void DisplayNutrition()
//    {
//        if (hasFoodInDatabase)
//        {
//            DateTime bufferDate = new DateTime();
//            float protein = 0;
//            float fat = 0;
//            float carbohydrate = 0;
//            float calories = 0;
//
//            for (int i = 0; i < MainMenuLayout.dayOverviewLayoutPublic.mealPerDayDatabaseDataSet.MealPerDayDatabase.Rows.Count; i++)
//            {
//                bufferDate = DateTime.Parse(mainMenu.dayOverviewLayoutPublic.mealPerDayDatabaseDataSet.MealPerDayDatabase.Rows[i][1].ToString());
//
//                if (new DateTime(year, month, int.Parse
//                    (DayText.Text)).ToString("dd.MM.yyyy") == bufferDate.ToString("dd.MM.yyyy")
//                
//                    )
//                    {
//                        protein += float.Parse
//                    (mainMenu.dayOverviewLayoutPublic.mealPerDayDatabaseDataSet.MealPerDayDatabase.Rows[i][4].ToString()
//                    );
//                        fat += float.Parse
//                    (mainMenu.dayOverviewLayoutPublic.mealPerDayDatabaseDataSet.MealPerDayDatabase.Rows[i][5].ToString()
//                    );
//                        carbohydrate += float.Parse
//                    (MainMenuLayout.dayOverviewLayoutPublic.mealPerDayDatabaseDataSet.MealPerDayDatabase.Rows[i][6].ToString()
//                    );
//                        calories += float.Parse
//                    (MainMenuLayout.dayOverviewLayoutPublic.mealPerDayDatabaseDataSet.MealPerDayDatabase.Rows[i][7].ToString()
//                
//            
//            );
//                    }
//                }
//
//                if ((protein + fat + carbohydrate + calories) > 0)
//            {
//                MainMenuLayout.ProteinSumSelectedPublic.Text = protein.ToString();
//                MainMenuLayout.FatsSumSelectedPublic.Text = fat.ToString();
//                MainMenuLayout.CarbohydratesSumSelectedPublic.Text = carbohydrate.ToString();
//                MainMenuLayout.CaloriesSumSelectedPublic.Text = calories.ToString();
//            }
//        } else
//        {
//            MainMenuLayout.ProteinSumSelectedPublic.Text = "-";
//            MainMenuLayout.FatsSumSelectedPublic.Text = "-";
//            MainMenuLayout.CarbohydratesSumSelectedPublic.Text = "-";
//            MainMenuLayout.CaloriesSumSelectedPublic.Text = "-";
//        }
//    }
    
    public void ClearSelection()
    {
        finalState = PaintingState.clearSelection;
        revalidate();
        repaint();
    }



    public void ClearCurrentDay()
    {
        isCurrentDay = false;
        revalidate();
        repaint();
    }

    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);

        Graphics2D g2D = (Graphics2D) g;
        Rectangle rect = new Rectangle();
        float thickness = 0.0F;
        Stroke oldStroke = g2D.getStroke();

        getBounds(rect);

        if (hasFoodInDatabase)
        {
            setBackground(new Color(100, 149, 237));
        }

        if (isCurrentDay)
        {
            thickness = 2.0F;

            g2D.setStroke(new BasicStroke(thickness));
            g2D.setColor(Color.GREEN);
            g2D.drawRect(0, 0, getWidth(), getHeight());
        }

        switch (finalState)
        {
            case mouseOver:
            {
                if (selectionIndex == MainMenuLayout.getLastSelectedDayIndex())
                {
                    thickness = 4.0F;

                    g2D.setStroke(new BasicStroke(thickness));
                    g2D.setColor(new Color(8, 104, 180));
                    g2D.drawRect(0, 0, getWidth(), getHeight());
                } else
                {
                    thickness = 4.0F;

                    g2D.setStroke(new BasicStroke(thickness));
                    g2D.setColor(new Color(127, 127, 127));
                    g2D.drawRect(0, 0, getWidth(), getHeight());
                }
            }
            break;
            case mouseLeft:
            {
                if (!isCurrentDay)
                {
                    if (selectionIndex == MainMenuLayout.getLastSelectedDayIndex())
                    {
                        thickness = 4.0F;

                        g2D.setStroke(new BasicStroke(thickness));
                        g2D.setColor(new Color(66, 156, 227));
                        g2D.drawRect(0, 0, getWidth(), getHeight());
                    } else
                    {
                        thickness = 4.0F;

                        g2D.setStroke(new BasicStroke(thickness));
                        g2D.setColor(new Color(0, 0, 0, 0));
                        g2D.drawRect(0, 0, getWidth(), getHeight());
                    }
                }
            }
            break;
            case mouseDown:
            {
                if (!isCurrentDay)
                {
                    if (selectionIndex == MainMenuLayout.getLastSelectedDayIndex())
                    {
                        thickness = 4.0F;

                        g2D.setStroke(new BasicStroke(thickness));
                        g2D.setColor(new Color(5, 112, 199));
                        g2D.drawRect(0, 0, getWidth(), getHeight());
                    } else
                    {
                        thickness = 4.0F;

                        g2D.setStroke(new BasicStroke(thickness));
                        g2D.setColor(new Color(170, 170, 170));
                        g2D.drawRect(0, 0, getWidth(), getHeight());
                    }
                }
            }
            break;
            case mouseUp:
            {
                if (!isCurrentDay)
                {
                    thickness = 4.0F;

                    g2D.setStroke(new BasicStroke(thickness));
                    g2D.setColor(new Color(8, 104, 180));
                    g2D.drawRect(0, 0, getWidth(), getHeight());
                }
            }
            break;
            case clearSelection:
            {
            }
            break;
        }

        g2D.setStroke(oldStroke);
    }
}
